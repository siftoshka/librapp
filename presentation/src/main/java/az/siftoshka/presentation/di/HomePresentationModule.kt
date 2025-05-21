package az.siftoshka.presentation.di

import az.siftoshka.presentation.feature.home.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {

    viewModelOf(::HomeViewModel)
}