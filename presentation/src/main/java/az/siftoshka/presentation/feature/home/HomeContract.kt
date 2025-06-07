package az.siftoshka.presentation.feature.home

import az.siftoshka.domain.entity.DashboardResponseListModel
import az.siftoshka.presentation.feature.base.UIEffect
import az.siftoshka.presentation.feature.base.UIEvent
import az.siftoshka.presentation.feature.base.UIState
import az.siftoshka.presentation.uikit.utils.UiText

class HomeContract {
    sealed class Event : UIEvent {
    }

    data class State(
        val isLoading: Boolean = false,
        val list: List<DashboardResponseListModel> = emptyList()
    ) : UIState

    sealed class Effect : UIEffect {
        data class OnFailure(val message: UiText) : Effect()
    }
}