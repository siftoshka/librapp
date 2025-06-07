package az.siftoshka.presentation.uikit.snackbar.util

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class BaseSnackbarState {
    private val _message = mutableStateOf<String?>(null)
    val message: State<String?> = _message

    private val _type = mutableStateOf<BaseSnackbarColor>(BaseSnackbarColor.TextWhite)
    val type: State<BaseSnackbarColor> = _type

    var updateState by mutableStateOf(false)
        private set

    fun showSnackbar(message: String) {
        _type.value = BaseSnackbarColor.TextWhite
        _message.value = message
        updateState = !updateState
    }

    fun showErrorSnackbar(message: String) {
        _type.value = BaseSnackbarColor.ERROR
        _message.value = message
        updateState = !updateState
    }

    fun showSuccessSnackbar(message: String) {
        _type.value = BaseSnackbarColor.SUCCESS
        _message.value = message
        updateState = !updateState
    }

    fun isNotEmpty(): Boolean {
        return _message.value != null
    }
}