package az.siftoshka.presentation.uikit.base

import androidx.activity.BackEventCompat
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun PredictiveSurface(
    onBack: () -> Unit,
    color: Color = MaterialTheme.colorScheme.tertiary,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val backPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var cornerRadius by remember { mutableFloatStateOf(0f) }
    var backProgress by remember { mutableFloatStateOf(0f) }
    val coroutineScope = rememberCoroutineScope()
    val scale = remember { Animatable(1f) }

    val onBackCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackProgressed(backEvent: BackEventCompat) {
                coroutineScope.launch {
                    backProgress = backEvent.progress
                    val targetScale = 1f - (backEvent.progress * 0.3f)
                    cornerRadius = backEvent.progress * 48f

                    if (backEvent.progress >= 1f) {
                        scale.animateTo(
                            targetValue = 0f,
                        ) { coroutineScope.launch { scale.snapTo(1f) } }
                        cornerRadius = 0f
                    } else {
                        scale.snapTo(targetScale)
                    }
                }
            }

            override fun handleOnBackPressed() {
                if (backProgress < 0.5f) {
                    cornerRadius = 0f
                    coroutineScope.launch {
                        scale.animateTo(
                            targetValue = 0f,
                            animationSpec = tween(150)
                        ) {
                            coroutineScope.launch { scale.snapTo(1f) }
                        }
                    }
                } else {
                    onBack.invoke()
                }
            }

            override fun handleOnBackCancelled() {
                cornerRadius = 0f
            }
        }
    }

    DisposableEffect(backPressedDispatcher) {
        backPressedDispatcher?.addCallback(onBackCallback)

        onDispose { onBackCallback.remove() }
    }

    Surface(
        color = color,
        modifier = modifier
            .fillMaxSize()
            .scale(scale.value)
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(color)
    ) { content() }
}