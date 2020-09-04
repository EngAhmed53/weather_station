package com.shouman.apps.weatherstation.repository

import androidx.lifecycle.Transformations
import com.shouman.apps.reseller.admin.data.database.WeatherDatabase
import com.shouman.apps.weatherstation.data.model.PreferenceCurrentCondition
import com.shouman.apps.weatherstation.data.model.PreferenceLocation
import com.shouman.apps.weatherstation.data.preferences.SharedPreferenceLiveData
import com.shouman.apps.weatherstation.data.preferences.UserPreferences

class MainRepository(
    weatherDatabase: WeatherDatabase,
    private val userPreferences: UserPreferences
) {

//    private val MAX_TIME_LIMIT: Long = 1800000L

    val locationLiveData = Transformations.map(
        SharedPreferenceLiveData(userPreferences, userPreferences.LOCATION_KEY)){
        it as? PreferenceLocation
    }

    val next5DaysData = weatherDatabase.weatherDAO.getNext5DaysCondition()

    val next12HourLiveData = weatherDatabase.weatherDAO.getCurrentDayNext12HourCondition()

    val currentConditionLiveData = Transformations.map(
        SharedPreferenceLiveData(userPreferences, userPreferences.CURRENT_CONDITION)
    ) {
//        if (it == null) {

//        }
//        val updatedTime = (it as? PreferenceCurrentCondition)?.updateTime ?: 0L
//
//        if (System.currentTimeMillis() - updatedTime >= MAX_TIME_LIMIT) {

//        }

        it as? PreferenceCurrentCondition
    }

    fun updateLocation(preferenceLocation: PreferenceLocation?) {
        preferenceLocation?.let {
            userPreferences.saveLocation(preferenceLocation)
            //start task to update current condition and all data
        }
    }

    fun updateCurrentCondition(currentCondition: PreferenceCurrentCondition?) {
        currentCondition?.let {
            userPreferences.saveCurrentCondition(currentCondition)
        }
    }
}