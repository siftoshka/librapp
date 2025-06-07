package az.siftoshka.presentation.feature.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import az.siftoshka.domain.entity.AppResponseModel
import az.siftoshka.domain.entity.DashboardResponseListModel
import az.siftoshka.presentation.uikit.app.AppIcon
import az.siftoshka.presentation.uikit.app.MoreIcon
import az.siftoshka.presentation.uikit.spacing

@Composable
fun AppList(appList: DashboardResponseListModel, onPerformClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { onPerformClick() }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            AppsRow(appList.apps) { onPerformClick() }
            BottomSection(appList.title)
        }
    }
}

@Composable
private fun AppsRow(apps: List<AppResponseModel>, onMoreClick: () -> Unit) {
    LazyRow(
        modifier = Modifier.width(360.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sp2),
    ) {
        if (apps.size >= 3) {
            items(apps.take(3)) { AppIcon(it) }
            item { MoreIcon { onMoreClick() } }
        } else {
            items(apps) { AppIcon(it) }
        }
    }
}

@Composable
private fun BottomSection(title: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.sp4)
                .padding(bottom = MaterialTheme.spacing.sp4)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}