package az.siftoshka.presentation.feature.onboarding

import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState
import az.siftoshka.presentation.uikit.utils.UiText

class OnboardingContract {
    sealed class Event : UIEvent {
        data class OnLogin(val jwt: String) : Event()
    }

    data class State(
        val isLoading: Boolean = false,
    ) : UIState

    sealed class Effect : UIEffect {
        data object OnNavHome : Effect()
        data class OnFailure(val message: UiText) : Effect()
    }
}