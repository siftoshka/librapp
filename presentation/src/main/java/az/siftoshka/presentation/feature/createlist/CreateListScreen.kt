package az.siftoshka.presentation.feature.createlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import az.siftoshka.presentation.uikit.base.PredictiveSurface
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreateListScreen(
    onBack: () -> Unit,
    viewModel: CreateListViewModel = koinViewModel()
) {
    PredictiveSurface(onBack = onBack) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "CreateListScreen",
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}
