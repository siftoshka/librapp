package az.siftoshka.presentation.uikit.snackbar.util

sealed class BaseSnackbarDuration(val time: Long) {
    data object LONG : BaseSnackbarDuration(5000L)
    data object SHORT : BaseSnackbarDuration(1000L)
    data object INFINITE : BaseSnackbarDuration(6000000L)
}