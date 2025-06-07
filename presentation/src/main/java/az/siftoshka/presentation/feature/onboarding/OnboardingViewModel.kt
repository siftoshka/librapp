package az.siftoshka.presentation.feature.onboarding

import az.siftoshka.data.datastore.AppSettingsPreferencesManager
import az.siftoshka.domain.exceptions.GlobalErrorResponse
import az.siftoshka.domain.usecase.LoginUseCase
import az.siftoshka.presentation.feature.base.BaseViewModel
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.Effect
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.Event
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.State

class OnboardingViewModel(
    private val loginUseCase: LoginUseCase,
    private val appManager: AppSettingsPreferencesManager
) : BaseViewModel<State, Effect, Event>() {

    override fun setInitialState() = State()

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.OnLogin -> onLogin(event.jwt)
        }
    }

    private fun onLogin(token: String) = launch {
        loginUseCase.invoke(
            params = token,
            handleLoading = { postState { copy(isLoading = it) } },
            onSuccess = { storeToken() },
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

    private fun storeToken() = launch {
        appManager.changeLoginStatus(true)
        postEffect { Effect.OnNavHome }
    }
}