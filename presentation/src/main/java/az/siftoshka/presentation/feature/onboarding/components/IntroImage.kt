package az.siftoshka.presentation.feature.onboarding.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import az.siftoshka.presentation.uikit.LibrappTheme
import az.siftoshka.presentation.uikit.extensions.meshGradient
import az.siftoshka.presentation.uikit.spacing
import az.siftoshka.presentation.uikit.utils.LibrappPreview

@Composable
fun IntroImage() {
    val topColor = MaterialTheme.colorScheme.primary
    val middleColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
    val bottomColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)

    Box(
        Modifier
            .padding(vertical = MaterialTheme.spacing.sp4)
            .fillMaxWidth()
            .height(300.dp)
            .clip(RoundedCornerShape(24.dp))
            .meshGradient(
                resolutionX = 32,
                resolutionY = 32,
                points = listOf(
                    listOf(
                        Offset(0f, 0f) to topColor,
                        Offset(.5f, 0f) to topColor,
                        Offset(1f, 0f) to topColor,
                    ),
                    listOf(
                        Offset(0f, .5f) to middleColor,
                        Offset(.5f, .5f) to middleColor,
                        Offset(1f, .5f) to middleColor,
                    ),
                    listOf(
                        Offset(0f, 1f) to bottomColor,
                        Offset(.5f, 1f) to bottomColor,
                        Offset(1f, 1f) to bottomColor,
                    ),
                ),
            )
    )
}

@LibrappPreview
@Composable
private fun Preview() {
    LibrappTheme {
        IntroImage()
    }
}