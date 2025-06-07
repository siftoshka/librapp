package az.siftoshka.presentation.navigation.home

import androidx.navigation.NavGraphBuilder
import az.siftoshka.presentation.feature.home.HomeScreen
import az.siftoshka.presentation.uikit.utils.animatedComposable

fun NavGraphBuilder.homeNavigation(
    onBack: () -> Unit,
) {
    animatedComposable<HomeRoute.Home> {
        HomeScreen()
    }
}