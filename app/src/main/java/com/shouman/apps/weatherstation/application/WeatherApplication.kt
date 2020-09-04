package com.shouman.apps.weatherstation.application

import android.app.Application
import com.shouman.apps.weatherstation.diModules.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@WeatherApplication)
            modules(appModule)
        }
    }

}