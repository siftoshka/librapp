package az.siftoshka.presentation.uikit.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarColor
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarDuration
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarPosition
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarState
import az.siftoshka.presentation.uikit.spacing
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
internal fun BaseSnackbarComponent(
    state: BaseSnackbarState,
    duration: BaseSnackbarDuration,
    position: BaseSnackbarPosition,
    containerColor: BaseSnackbarColor,
    contentColor: BaseSnackbarColor,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector?,
    enterAnimation: EnterTransition,
    exitAnimation: ExitTransition,
    withDismissAction: Boolean
) {
    var showSnackbar by remember { mutableStateOf(false) }
    val message by rememberUpdatedState(newValue = state.message.value)
    val color by rememberUpdatedState(newValue = state.type.value)
    val container = if (color is BaseSnackbarColor.TextWhite) containerColor else color
    val timer = Timer("Animation Timer", true)

    DisposableEffect(state.updateState) {
        showSnackbar = true
        timer.schedule(duration.time) {
            showSnackbar = false
        }
        onDispose {
            timer.cancel()
            timer.purge()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(vertical = MaterialTheme.spacing.sp4),
        verticalArrangement = when (position) {
            is BaseSnackbarPosition.Top -> Arrangement.Top
            is BaseSnackbarPosition.Bottom -> Arrangement.Bottom
            is BaseSnackbarPosition.Float -> Arrangement.Top
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedVisibility(
            visible = state.isNotEmpty() && showSnackbar,
            enter = when (position) {
                is BaseSnackbarPosition.Top -> enterAnimation
                is BaseSnackbarPosition.Bottom -> enterAnimation
                is BaseSnackbarPosition.Float -> fadeIn()
            },
            exit = when (position) {
                is BaseSnackbarPosition.Top -> exitAnimation
                is BaseSnackbarPosition.Bottom -> exitAnimation
                is BaseSnackbarPosition.Float -> fadeOut()
            }
        ) {
            CoreBaseSnackbar(
                message = message,
                position = position,
                containerColor = container,
                contentColor = contentColor,
                verticalPadding = verticalPadding,
                horizontalPadding = horizontalPadding,
                icon = icon,
                withDismissAction = withDismissAction,
                onDismiss = {
                    if (withDismissAction) {
                        showSnackbar = false
                        timer.cancel()
                        timer.purge()
                    }
                }
            )
        }
    }
}