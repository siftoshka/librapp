package az.siftoshka.presentation.feature.home

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
fun HomeScreen(
    onBack: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
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
                text = "HomeScreen",
                style = MaterialTheme.typography.headlineLarge,
            )
        }
    }
}