package az.siftoshka.presentation.feature.onboarding.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.siftoshka.presentation.R
import az.siftoshka.presentation.uikit.LibrappTheme
import az.siftoshka.presentation.uikit.spacing
import az.siftoshka.presentation.uikit.utils.LibrappPreview
import kotlinx.coroutines.delay

@Composable
fun IntroTexts() {
    val textList = listOf(
        R.string.text_onboarding_feature_1,
        R.string.text_onboarding_feature_2,
        R.string.text_onboarding_feature_3
    )
    val visibleStates = remember { textList.map { mutableStateOf(false) } }

    LaunchedEffect(visibleStates) {
        textList.indices.forEach { index ->
            delay(800L)
            visibleStates[index].value = true
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sp1)
    ) {
        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        textList.forEachIndexed { index, textId ->
            AnimatedVisibility(
                visible = visibleStates[index].value,
                enter = fadeIn(animationSpec = tween(500)) + slideInVertically(
                    animationSpec = tween(500),
                    initialOffsetY = { -it / 2 }
                )
            ) {
                Text(
                    text = stringResource(textId),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = MaterialTheme.spacing.sp2)
                )
            }
        }
    }
}

@LibrappPreview
@Composable
private fun Preview() {
    LibrappTheme {
        IntroTexts()
    }
}