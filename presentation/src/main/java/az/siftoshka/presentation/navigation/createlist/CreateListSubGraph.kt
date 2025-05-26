package az.siftoshka.presentation.navigation.createlist

import az.siftoshka.presentation.navigation.SubGraph
import kotlinx.serialization.Serializable

sealed class CreateListSubGraph : SubGraph {

    @Serializable
    data object Main : SubGraph
}
