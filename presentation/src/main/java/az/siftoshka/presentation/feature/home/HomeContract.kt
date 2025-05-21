package az.siftoshka.presentation.feature.home

import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState

class HomeContract {
    sealed class Event : UIEvent {
    }

    data object State : UIState

    sealed class Effect : UIEffect {
    }
}