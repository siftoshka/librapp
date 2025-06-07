package az.siftoshka.presentation.feature.home

import az.siftoshka.domain.exceptions.GlobalErrorResponse
import az.siftoshka.domain.usecase.GetDashboardUseCase
import az.siftoshka.presentation.feature.base.BaseViewModel
import az.siftoshka.presentation.feature.home.HomeContract.Effect
import az.siftoshka.presentation.feature.home.HomeContract.Event
import az.siftoshka.presentation.feature.home.HomeContract.State

class HomeViewModel(
    private val getDashboardUseCase: GetDashboardUseCase
) : BaseViewModel<State, Effect, Event>() {

    override fun setInitialState() = State()

    init {
        getDashboard()
    }

    private fun getDashboard() = launch {
        getDashboardUseCase.invoke(
            params = Unit,
            handleLoading = { postState { copy(isLoading = it) } },
            onSuccess = { postState { copy(list = list) } },
            onError = { _, error ->
                when (error) {
                    is GlobalErrorResponse.ClientErrorResponse -> {
                        onFailure(
                            message = error.errorResponse?.message,
                            onResult = { postEffect { Effect.OnFailure(it) } }
                        )
                    }

                    is GlobalErrorResponse.NetworkResponse -> {
                        onFailure(onResult = { postEffect { Effect.OnFailure(it) } })
                    }
                }
            }
        )
    }
}