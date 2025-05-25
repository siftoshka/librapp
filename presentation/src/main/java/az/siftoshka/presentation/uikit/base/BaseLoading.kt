package az.siftoshka.presentation.uikit.base

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp

@Composable
fun BaseLoading(isLoading: Boolean) {
    val keyboardController = LocalSoftwareKeyboardController.current

    AnimatedVisibility(
        modifier = Modifier.fillMaxSize(),
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        keyboardController?.hide()
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.5f))
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}