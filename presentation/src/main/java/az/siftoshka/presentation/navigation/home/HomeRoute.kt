package az.siftoshka.presentation.navigation.home

import az.siftoshka.presentation.navigation.Screen
import kotlinx.serialization.Serializable

sealed class HomeRoute : Screen {

    @Serializable
    data object Home : Screen
}