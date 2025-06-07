package az.siftoshka.presentation.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import az.siftoshka.presentation.R
import az.siftoshka.presentation.feature.home.HomeContract.Effect
import az.siftoshka.presentation.feature.home.components.AppList
import az.siftoshka.presentation.uikit.LocalSnackbar
import az.siftoshka.presentation.uikit.base.BaseLoading
import az.siftoshka.presentation.uikit.snackbar.BaseSnackbar
import az.siftoshka.presentation.uikit.spacing
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val snackbar = LocalSnackbar.current
    val state by viewModel.state.collectAsState()

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is Effect.OnFailure -> {
                    snackbar.showErrorSnackbar(effect.message.asString(context))
                }
            }
        }
    }

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(
                        start = MaterialTheme.spacing.sp4,
                        end = MaterialTheme.spacing.sp4,
                        top = MaterialTheme.spacing.sp1,
                        bottom = MaterialTheme.spacing.sp2
                    ),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.sp2),
                ) {
                    items(state.list) {
                        AppList(it) {
                            snackbar.showSnackbar(context.getString(R.string.error_not_implemented))
                        }
                    }
                }
            }
        }

        BaseSnackbar(state = snackbar)
        BaseLoading(isLoading = state.isLoading)
    }
}