package az.siftoshka.presentation.di

import az.siftoshka.presentation.feature.createlist.CreateListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val createListPresentationModule = module {

    viewModelOf(::CreateListViewModel)
}
