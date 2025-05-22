package az.siftoshka.presentation.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import az.siftoshka.presentation.feature.onboarding.components.IntroImage
import az.siftoshka.presentation.feature.onboarding.components.IntroTexts
import az.siftoshka.presentation.uikit.button.GoogleButton
import az.siftoshka.presentation.uikit.snackbar.BaseSnackbar
import az.siftoshka.presentation.uikit.snackbar.rememberBaseSnackbarState
import az.siftoshka.presentation.uikit.spacing
import az.siftoshka.presentation.uikit.utils.onGoogleLogout
import org.koin.androidx.compose.koinViewModel

@Composable
fun OnboardingScreen(
    onNavHome: () -> Unit,
    viewModel: OnboardingViewModel = koinViewModel()
) {
    val snackbar = rememberBaseSnackbarState()
    val context = LocalContext.current

    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect { effect ->
            when (effect) {
                OnboardingContract.Effect.OnNavHome -> {
                    context.onGoogleLogout()
                    onNavHome()
                }

                is OnboardingContract.Effect.OnFailure -> {
                    snackbar.showErrorSnackbar(effect.message.asString(context))
                }
            }
        }
    }

    BaseSnackbar(state = snackbar)

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .systemBarsPadding()
    ) {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.sp4)) {
            IntroImage()
            IntroTexts()
            Spacer(modifier = Modifier.weight(1f))
            GoogleButton(modifier = Modifier.fillMaxWidth()) {
                viewModel.postEvent { OnboardingContract.Event.OnLogin(it) }
            }
        }
    }
}