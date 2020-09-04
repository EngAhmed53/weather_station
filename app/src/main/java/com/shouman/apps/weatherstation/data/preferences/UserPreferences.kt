package com.shouman.apps.weatherstation.data.preferences

import android.content.SharedPreferences
import com.google.gson.Gson
import com.shouman.apps.weatherstation.data.model.PreferenceCurrentCondition
import com.shouman.apps.weatherstation.data.model.PreferenceLocation

class UserPreferences(val sharedPreferences: SharedPreferences) {

    val LOCATION_KEY: String = "location"
    val CURRENT_CONDITION = "currentCondition"


    fun saveLocation(preferenceLocation: PreferenceLocation) {

        val gson = Gson()

        val preferenceLocationJson = gson.toJson(preferenceLocation)

        sharedPreferences.edit().putString(LOCATION_KEY, preferenceLocationJson).apply()

    }

    fun getSavedLocation(): PreferenceLocation? {

        val gson = Gson()

        val preferenceLocationString = sharedPreferences.getString(LOCATION_KEY, null)

        return gson.fromJson(preferenceLocationString, PreferenceLocation::class.java)
    }


    fun saveCurrentCondition(currentCondition: PreferenceCurrentCondition) {

        val gson = Gson()

        val preferenceLocationJson = gson.toJson(currentCondition)

        sharedPreferences.edit().putString(CURRENT_CONDITION, preferenceLocationJson).apply()
    }

    fun getSavedCurrentCondition(): PreferenceCurrentCondition? {

        val gson = Gson()

        val preferenceLocationString = sharedPreferences.getString(CURRENT_CONDITION, null)

        return gson.fromJson(preferenceLocationString, PreferenceCurrentCondition::class.java)
    }
}
