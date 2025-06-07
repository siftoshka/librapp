package az.siftoshka.presentation.feature.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.siftoshka.domain.base.BaseUseCase
import az.siftoshka.domain.base.FlowUseCase
import az.siftoshka.domain.entity.base.RemoteResponse
import az.siftoshka.domain.exceptions.GlobalErrorResponse
import az.siftoshka.presentation.R
import az.siftoshka.presentation.uikit.utils.UiText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State : UIState, Effect : UIEffect, Event : UIEvent> : ViewModel() {

    private val initialState: State by lazy { setInitialState() }
    abstract fun setInitialState(): State

    private val _state = MutableStateFlow(initialState)
    val state: StateFlow<State> = _state

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _event = MutableSharedFlow<Event>()
    val event: SharedFlow<Event> = _event

    fun currentViewState(): State = state.value

    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch(context, start) {
            block()
        }
    }

    init {
        subscribeEvents()
    }

    protected fun postState(producer: State.() -> State) {
        viewModelScope.launch {
            val newState = state.value.producer()
            newState.let {
                _state.emit(it)
            }
        }
    }

    protected fun postEffect(producer: () -> Effect) {
        viewModelScope.launch { _effect.send(producer()) }
    }

    fun postEvent(event: () -> Event) {
        viewModelScope.launch { _event.emit(event()) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch {
            _event.collect { handleEvents(it) }
        }
    }

    protected open fun handleEvents(event: Event) {}

    fun <T, R> BaseUseCase<T, R>.invoke(
        params: T,
        handleLoading: ((Boolean) -> Unit)? = null,
        onSuccess: ((result: R) -> Unit)? = null,
        onError: ((errorResponse: GlobalErrorResponse) -> Unit)? = null,
    ) {
        viewModelScope.launch {
            handleLoading?.invoke(true)
            try {
                onSuccess?.invoke(call(params))
            } catch (e: Exception) {
                onError?.invoke(GlobalErrorResponse.NetworkResponse(e))
            }
            handleLoading?.invoke(false)
        }
    }

    suspend fun <T, R> FlowUseCase<T, R>.invoke(
        params: T,
        handleLoading: ((Boolean) -> Unit)? = null,
        onStart: (() -> Unit)? = null,
        onSuccess: ((result: R) -> Unit)? = null,
        onFinish: (() -> Unit)? = null,
        onError: ((errorCode: Int, errorResponse: GlobalErrorResponse) -> Unit)? = null,
    ) {
        call(params)
            .onStart {
                onStart?.invoke()
                handleLoading?.invoke(true)
            }
            .onEach { result ->
                handleLoading?.invoke(false)
                when (result) {
                    is RemoteResponse.Success<R> -> {
                        onSuccess?.invoke(result.result)
                    }

                    is RemoteResponse.Error -> {
                        onError?.invoke(result.errorCode, result.errorResponse)
                    }
                }
            }
            .onCompletion { error ->
                handleLoading?.invoke(false)
                onFinish?.invoke()
            }
            .launchIn(viewModelScope)
    }

    internal fun onFailure(
        message: String? = null,
        resId: Int? = null,
        onResult: (message: UiText) -> Unit
    ) {
        if (message != null) {
            onResult(UiText.DynamicString(message))
        } else {
            onResult(UiText.StringResource(resId ?: R.string.error_something_went_wrong))
        }
    }
}

interface UIState

interface UIEvent

interface UIEffect
