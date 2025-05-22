package az.siftoshka.presentation.uikit.snackbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarState

@Composable
fun rememberBaseSnackbarState(): BaseSnackbarState {
    return remember { BaseSnackbarState() }
}