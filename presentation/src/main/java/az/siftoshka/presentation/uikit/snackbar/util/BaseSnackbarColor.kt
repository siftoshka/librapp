package az.siftoshka.presentation.uikit.snackbar.util

import androidx.compose.ui.graphics.Color

sealed class BaseSnackbarColor(val color: Color) {
    data object SUCCESS : BaseSnackbarColor(Color(0xFF19B661))
    data object ERROR : BaseSnackbarColor(Color(0xFFE8503A))
    data object TextWhite : BaseSnackbarColor(Color(0xFFEEEEEE))
    data class CustomColor(val customColor: Color) : BaseSnackbarColor(customColor)
}