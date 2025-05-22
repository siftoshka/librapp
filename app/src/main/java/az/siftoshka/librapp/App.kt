package az.siftoshka.librapp

import android.app.Application
import az.siftoshka.data.di.dataModule
import az.siftoshka.data.di.networkModule
import az.siftoshka.domain.di.useCaseModule
import az.siftoshka.presentation.di.homePresentationModule
import az.siftoshka.presentation.di.onboardingPresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androix.startup.KoinStartup
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.KoinConfiguration

@OptIn(KoinExperimentalAPI::class)
class App: Application(), KoinStartup {

    override fun onKoinStartup() = KoinConfiguration {
        androidLogger()
        androidContext(this@App)

        modules(
            dataModule,
            networkModule,
            useCaseModule,
            onboardingPresentationModule,
            homePresentationModule
        )
    }
}