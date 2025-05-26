package az.siftoshka.presentation.navigation.createlist

import androidx.navigation.NavGraphBuilder
import az.siftoshka.presentation.feature.createlist.CreateListScreen
import az.siftoshka.presentation.uikit.utils.animatedComposable

fun NavGraphBuilder.createListNavigation(
    onBack: () -> Unit,
) {
    animatedComposable<CreateListRoute.CreateList> {
        CreateListScreen(
            onBack = onBack
        )
    }
}
