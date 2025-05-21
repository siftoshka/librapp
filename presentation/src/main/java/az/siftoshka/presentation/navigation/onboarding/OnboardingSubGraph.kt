package az.siftoshka.presentation.navigation.onboarding

import az.siftoshka.presentation.navigation.SubGraph
import kotlinx.serialization.Serializable

sealed class OnboardingSubGraph : SubGraph {

    @Serializable
    data object Main : SubGraph

}