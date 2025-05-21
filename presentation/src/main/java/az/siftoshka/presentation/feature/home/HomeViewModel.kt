package az.siftoshka.presentation.feature.home

import az.siftoshka.presentation.feature.base.BaseViewModel
import az.siftoshka.presentation.feature.home.HomeContract.Effect
import az.siftoshka.presentation.feature.home.HomeContract.Event
import az.siftoshka.presentation.feature.home.HomeContract.State

class HomeViewModel() : BaseViewModel<State, Effect, Event>() {

    override fun setInitialState() = State
}