package az.siftoshka.presentation.feature.onboarding

import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState

class OnboardingContract {
    sealed class Event : UIEvent {
        data object OnLogin : Event()
    }

    data object State : UIState

    sealed class Effect : UIEffect {
        data object OnNavHome : Effect()
    }
}