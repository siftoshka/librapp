package az.siftoshka.presentation.navigation.home

import az.siftoshka.presentation.navigation.SubGraph
import kotlinx.serialization.Serializable

sealed class HomeSubGraph : SubGraph {

    @Serializable
    data object Main : SubGraph

}