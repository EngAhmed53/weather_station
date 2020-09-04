package com.shouman.apps.weatherstation.diModules

import androidx.preference.PreferenceManager
import com.shouman.apps.reseller.admin.data.database.WeatherDatabase
import com.shouman.apps.weatherstation.api.NetworkCall
import com.shouman.apps.weatherstation.data.preferences.UserPreferences
import com.shouman.apps.weatherstation.repository.MainRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module {

    // locationService
    single { NetworkCall.locationService }

    // currentConditionsService
    single { NetworkCall.currentConditionsService }

    // next12HourApiServices
    single { NetworkCall.next12HourApiServices }

    // next5DaysApiServices
    single { NetworkCall.next5DaysApiServices }

    // weather database
    single { WeatherDatabase }

    // sharedPreference
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

    // usersPreference
    single { UserPreferences(get()) }

    // mainRepository
    single { MainRepository(get(), get()) }

}