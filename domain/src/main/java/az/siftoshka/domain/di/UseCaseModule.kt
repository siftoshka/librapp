package az.siftoshka.domain.di

import az.siftoshka.domain.usecase.GetDashboardUseCase
import az.siftoshka.domain.usecase.LoginUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { GetDashboardUseCase(get()) }
}