package az.siftoshka.librapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.navigation
import az.siftoshka.presentation.navigation.SubGraph
import az.siftoshka.presentation.navigation.home.HomeRoute
import az.siftoshka.presentation.navigation.home.HomeSubGraph
import az.siftoshka.presentation.navigation.home.homeNavigation
import az.siftoshka.presentation.navigation.home.navHome
import az.siftoshka.presentation.navigation.onboarding.OnboardingRoute
import az.siftoshka.presentation.navigation.onboarding.OnboardingSubGraph
import az.siftoshka.presentation.navigation.onboarding.onboardingNavigation

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: SubGraph
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation<OnboardingSubGraph.Main>(startDestination = OnboardingRoute.Onboarding) {
            onboardingNavigation(
                onNavHome = navController::navHome
            )
        }
        navigation<HomeSubGraph.Main>(startDestination = HomeRoute.Home) {
            homeNavigation(
                onBack = navController::navigateUp
            )
        }
    }
}