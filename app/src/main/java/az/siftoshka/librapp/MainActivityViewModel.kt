package az.siftoshka.librapp

import az.siftoshka.data.datastore.AppSettingsPreferencesManager
import az.siftoshka.librapp.MainActivityContract.Effect
import az.siftoshka.librapp.MainActivityContract.Event
import az.siftoshka.librapp.MainActivityContract.State
import az.siftoshka.presentation.feature.base.BaseViewModel
import az.siftoshka.presentation.navigation.home.HomeSubGraph
import az.siftoshka.presentation.navigation.onboarding.OnboardingSubGraph

class MainActivityViewModel(
    private val appManager: AppSettingsPreferencesManager
) : BaseViewModel<State, Effect, Event>() {

    override fun setInitialState() = State()

    init {
        getInitialRouteDestination()
    }

    private fun getInitialRouteDestination() = launch {
        appManager.getAppSettings().collect {
            if (it.isLoggedIn) {
                postState { copy(startDestination = HomeSubGraph.Main) }
            } else {
                postState { copy(startDestination = HomeSubGraph.Main) }
            }
        }
    }
}