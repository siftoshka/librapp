package az.siftoshka.presentation.di

import az.siftoshka.presentation.feature.onboarding.OnboardingViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val onboardingPresentationModule = module {

    viewModelOf(::OnboardingViewModel)
}