package com.shouman.apps.weatherstation.data.preferences

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

class SharedPreferenceLiveData(
    private val userPreferences: UserPreferences,
    private val mKey: String
) : LiveData<IPreference?>() {


    private val preferenceListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == mKey) {
                value = when (key) {
                    userPreferences.LOCATION_KEY -> {
                        userPreferences.getSavedLocation()
                    }
                    userPreferences.CURRENT_CONDITION -> {
                        userPreferences.getSavedCurrentCondition()
                    }
                    else -> null
                }
            }
        }

    override fun onActive() {
        super.onActive()
        value = when (mKey) {
            userPreferences.LOCATION_KEY -> {
                userPreferences.getSavedLocation()
            }
            userPreferences.CURRENT_CONDITION -> {
                userPreferences.getSavedCurrentCondition()
            }
            else -> null
        }
        userPreferences.sharedPreferences.registerOnSharedPreferenceChangeListener(
            preferenceListener
        )
    }


    override fun onInactive() {
        userPreferences.sharedPreferences.unregisterOnSharedPreferenceChangeListener(
            preferenceListener
        )
        super.onInactive()
    }
}