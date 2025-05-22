package az.siftoshka.data.di

import az.siftoshka.data.datastore.AppSettingsPreferencesManager
import az.siftoshka.data.repository.OnboardingRepositoryImpl
import az.siftoshka.domain.repository.OnboardingRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModule = module {
    singleOf(::AppSettingsPreferencesManager)
    single<OnboardingRepository> { OnboardingRepositoryImpl(get()) }
}