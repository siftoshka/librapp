package az.siftoshka.presentation.feature.createlist

import az.siftoshka.presentation.feature.base.BaseViewModel

class CreateListViewModel : BaseViewModel<CreateListContract.State, CreateListContract.Effect, CreateListContract.Event>() {

    override fun setInitialState() = CreateListContract.State
}
