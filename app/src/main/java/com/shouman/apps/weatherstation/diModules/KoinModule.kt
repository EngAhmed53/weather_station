package com.shouman.apps.weatherstation.diModules

import androidx.preference.PreferenceManager
import com.shouman.apps.reseller.admin.data.database.WeatherDatabase
import com.shouman.apps.weatherstation.R
import com.shouman.apps.weatherstation.api.NetworkCall
import com.shouman.apps.weatherstation.data.preferences.UserPreferences
import com.shouman.apps.weatherstation.repository.MainRepository
import com.shouman.apps.weatherstation.ui.LocationViewModel
import com.shouman.apps.weatherstation.ui.mainFragment.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
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
    single { WeatherDatabase.getInstance(androidApplication()) }

    // sharedPreference
    single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }

    // usersPreference
    single { UserPreferences(get()) }

    // mainRepository
    single { MainRepository(get(), get()) }

    //api_key
    single { androidApplication().getString(R.string.accu_key) }

    // locationViewModel
    viewModel { LocationViewModel(androidApplication()) }

    // mainViewModel
    viewModel { MainViewModel() }

}