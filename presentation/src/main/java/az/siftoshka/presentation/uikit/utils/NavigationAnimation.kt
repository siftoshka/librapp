package az.siftoshka.presentation.uikit.utils

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val TIME_DURATION = 300
private const val TIME_DURATION_FAST = 150

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val enterTransitionVertical: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
    {
        slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(
                durationMillis = TIME_DURATION_FAST,
                easing = LinearOutSlowInEasing
            )
        )
    }

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { -it / 3 },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { it },
        animationSpec = tween(durationMillis = TIME_DURATION, easing = LinearOutSlowInEasing)
    )
}

val popExitTransitionVertical: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
    {
        slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(
                durationMillis = TIME_DURATION_FAST,
                easing = LinearOutSlowInEasing
            )
        )
    }

inline fun <reified T : Any> NavGraphBuilder.animatedComposable(
    animatedOrientation: AnimationOrientation = AnimationOrientation.BOTH,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    when (animatedOrientation) {
        AnimationOrientation.START -> {
            composable<T>(
                enterTransition = enterTransition,
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = popExitTransition,
                content = content
            )
        }

        AnimationOrientation.END -> {
            composable<T>(
                enterTransition = { EnterTransition.None },
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = { ExitTransition.None },
                content = content
            )
        }

        AnimationOrientation.BOTH -> {
            composable<T>(
                enterTransition = enterTransition,
                exitTransition = exitTransition,
                popEnterTransition = popEnterTransition,
                popExitTransition = popExitTransition,
                content = content
            )
        }

        AnimationOrientation.NONE -> {
            composable<T>(
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = { ExitTransition.None },
                content = content
            )
        }

        AnimationOrientation.UP -> {
            composable<T>(
                enterTransition = enterTransitionVertical,
                exitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
                popExitTransition = popExitTransitionVertical,
                content = content
            )
        }
    }
}


enum class AnimationOrientation {
    START, END, BOTH, NONE, UP
}