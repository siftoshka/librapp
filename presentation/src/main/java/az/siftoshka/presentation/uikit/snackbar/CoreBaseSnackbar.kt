package az.siftoshka.presentation.uikit.snackbar

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarColor
import az.siftoshka.presentation.uikit.snackbar.util.BaseSnackbarPosition

@Composable
internal fun CoreBaseSnackbar(
    message: String?,
    position: BaseSnackbarPosition,
    containerColor: BaseSnackbarColor,
    contentColor: BaseSnackbarColor,
    verticalPadding: Dp,
    horizontalPadding: Dp,
    icon: ImageVector?,
    withDismissAction: Boolean,
    onDismiss: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(
                fraction = when (position) {
                    is BaseSnackbarPosition.Top -> 1f
                    is BaseSnackbarPosition.Bottom -> 1f
                    is BaseSnackbarPosition.Float -> 0.95f
                }
            )
            .background(
                color = containerColor.color,
                shape = when (position) {
                    is BaseSnackbarPosition.Top -> RectangleShape
                    is BaseSnackbarPosition.Bottom -> RectangleShape
                    is BaseSnackbarPosition.Float -> RoundedCornerShape(8.dp)
                }
            )
            .padding(vertical = verticalPadding)
            .padding(horizontal = horizontalPadding)
            .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(4f),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let {
                Icon(
                    imageVector = icon,
                    contentDescription = "Snackbar Icon",
                    tint = contentColor.color,
                )

                Spacer(modifier = Modifier.width(16.dp))
            }

            Text(
                text = message ?: "Unknown",
                color = contentColor.color,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
        if (withDismissAction) {
            IconButton(
                onClick = { onDismiss?.invoke() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Snackbar Dismiss Icon",
                    tint = contentColor.color
                )
            }
        }
    }
}