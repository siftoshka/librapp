package az.siftoshka.presentation.navigation.createlist

import androidx.navigation.NavController
import androidx.navigation.NavOptions

fun NavController.navCreateList(navOptions: NavOptions? = null) =
    navigate(CreateListRoute.CreateList, navOptions)
