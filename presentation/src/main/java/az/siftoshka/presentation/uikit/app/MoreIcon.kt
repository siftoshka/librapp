package az.siftoshka.presentation.uikit.app

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import az.siftoshka.presentation.R


@Composable
fun MoreIcon(onPerformClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.large,
        modifier = Modifier.size(60.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        onClick = { onPerformClick() }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_more),
            contentDescription = stringResource(R.string.text_common_continue),
            modifier = Modifier
                .size(60.dp)
                .padding(12.dp)
        )
    }
}