package az.siftoshka.presentation.navigation.onboarding

import androidx.navigation.NavGraphBuilder
import az.siftoshka.presentation.feature.onboarding.OnboardingScreen
import az.siftoshka.presentation.uikit.utils.animatedComposable

fun NavGraphBuilder.onboardingNavigation(
    onNavHome: () -> Unit,
) {
    animatedComposable<OnboardingRoute.Onboarding> {
        OnboardingScreen(
            onNavHome = onNavHome
        )
    }
}