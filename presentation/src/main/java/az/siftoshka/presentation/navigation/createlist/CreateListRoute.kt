package az.siftoshka.presentation.navigation.createlist

import az.siftoshka.presentation.navigation.Screen
import kotlinx.serialization.Serializable

sealed class CreateListRoute : Screen {

    @Serializable
    data object CreateList : Screen
}
