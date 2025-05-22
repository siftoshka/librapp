package az.siftoshka.presentation.uikit.snackbar

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarColor
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarDuration
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarPosition
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarState

@Composable
fun BaseSnackbar(
    modifier: Modifier = Modifier,
    state: BaseSnackbarState,
    position: BaseSnackbarPosition = BaseSnackbarPosition.Float,
    duration: BaseSnackbarDuration = BaseSnackbarDuration.SHORT,
    icon: ImageVector? = Icons.Default.Info,
    containerColor: BaseSnackbarColor = BaseSnackbarColor.CustomColor(MaterialTheme.colorScheme.tertiary),
    contentColor: BaseSnackbarColor = BaseSnackbarColor.TextWhite,
    enterAnimation: EnterTransition = expandVertically(
        animationSpec = tween(delayMillis = 300),
        expandFrom = when (position) {
            is BaseSnackbarPosition.Top -> Alignment.Top
            is BaseSnackbarPosition.Bottom -> Alignment.Bottom
            is BaseSnackbarPosition.Float -> Alignment.CenterVertically
        }
    ),
    exitAnimation: ExitTransition = shrinkVertically(
        animationSpec = tween(delayMillis = 300),
        shrinkTowards = when (position) {
            is BaseSnackbarPosition.Top -> Alignment.Top
            is BaseSnackbarPosition.Bottom -> Alignment.Bottom
            is BaseSnackbarPosition.Float -> Alignment.CenterVertically
        }
    ),
    verticalPadding: Dp = 12.dp,
    horizontalPadding: Dp = 12.dp,
    withDismissAction: Boolean = false
) {
    Box(modifier = modifier.fillMaxSize()) {
        BaseSnackbarComponent(
            state = state,
            duration = duration,
            position = position,
            containerColor = containerColor,
            contentColor = contentColor,
            verticalPadding = verticalPadding,
            horizontalPadding = horizontalPadding,
            icon = icon,
            enterAnimation = enterAnimation,
            exitAnimation = exitAnimation,
            withDismissAction = withDismissAction
        )
    }
}