package az.siftoshka.presentation.uikit.snackbar.util

sealed class BaseSnackbarPosition {
    object Top : BaseSnackbarPosition()
    object Bottom : BaseSnackbarPosition()
    object Float : BaseSnackbarPosition()
}