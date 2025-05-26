package az.siftoshka.presentation.feature.createlist

import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState

class CreateListContract {
    sealed class Event : UIEvent {
    }

    data object State : UIState

    sealed class Effect : UIEffect {
    }
}
