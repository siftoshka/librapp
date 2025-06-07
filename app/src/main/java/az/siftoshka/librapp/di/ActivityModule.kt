package az.siftoshka.librapp.di

import az.siftoshka.librapp.MainActivityViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val activityModule = module {

    viewModelOf(::MainActivityViewModel)
}
