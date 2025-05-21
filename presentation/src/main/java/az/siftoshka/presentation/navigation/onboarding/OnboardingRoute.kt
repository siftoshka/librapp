package az.siftoshka.presentation.navigation.onboarding

import az.siftoshka.presentation.navigation.Screen
import kotlinx.serialization.Serializable

sealed class OnboardingRoute : Screen {

    @Serializable
    data object Onboarding : Screen
}