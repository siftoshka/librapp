package az.siftoshka.presentation.feature.onboarding

import az.siftoshka.presentation.feature.base.BaseViewModel
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.Effect
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.Event
import az.siftoshka.presentation.feature.onboarding.OnboardingContract.State

class OnboardingViewModel() : BaseViewModel<State, Effect, Event>() {

    override fun setInitialState() = State
}