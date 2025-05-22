package az.siftoshka.presentation.uikit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * General spacing values of the application.
 */
data class Spacing(
    val sp1: Dp = 4.dp,
    val sp2: Dp = 8.dp,
    val sp3: Dp = 12.dp,
    val sp4: Dp = 16.dp,
    val sp5: Dp = 20.dp,
    val sp6: Dp = 24.dp,
    val sp8: Dp = 32.dp,
    val sp10: Dp = 40.dp,
    val sp12: Dp = 48.dp,
    val sp14: Dp = 56.dp,
    val sp16: Dp = 64.dp,
    val sp18: Dp = 72.dp,
    val sp20: Dp = 80.dp,
    val sp24: Dp = 96.dp,
    val sp32: Dp = 128.dp,
    val sp40: Dp = 160.dp,
    val sp48: Dp = 192.dp,
    val sp56: Dp = 224.dp,
    val sp64: Dp = 256.dp,
    val sp90: Dp = 360.dp,
    val sp96: Dp = 384.dp,
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current