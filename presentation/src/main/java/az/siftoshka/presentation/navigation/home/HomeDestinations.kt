package az.siftoshka.presentation.navigation.home

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navHome(navOptions: NavOptions? = null) =
    navigate(HomeRoute.Home, navOptions)