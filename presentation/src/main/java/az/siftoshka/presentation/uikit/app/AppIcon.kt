package az.siftoshka.presentation.uikit.app

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import az.siftoshka.domain.entity.AppResponseModel
import az.siftoshka.presentation.R
import az.siftoshka.presentation.uikit.utils.openUrl
import coil.compose.SubcomposeAsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun AppIcon(app: AppResponseModel) {
    val context = LocalContext.current

    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(60.dp),
        onClick = { context.openUrl(app.url) }
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(app.url)
                .error(R.drawable.ic_missing_icon)
                .diskCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = app.title,
            modifier = Modifier.size(60.dp)
        )
    }
}