package az.siftoshka.librapp

import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState
import az.siftoshka.presentation.navigation.SubGraph
import az.siftoshka.presentation.navigation.onboarding.OnboardingSubGraph

class MainActivityContract {
    sealed class Event : UIEvent {
    }

    data class State(
        val startDestination: SubGraph? = null
    ) : UIState

    sealed class Effect : UIEffect {
    }
}