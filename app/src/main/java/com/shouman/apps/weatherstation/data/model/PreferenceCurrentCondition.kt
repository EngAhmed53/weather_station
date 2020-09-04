package com.shouman.apps.weatherstation.data.model

import com.shouman.apps.weatherstation.api.networkModel.CurrentTemp
import com.shouman.apps.weatherstation.data.preferences.IPreference
import java.io.Serializable

data class PreferenceCurrentCondition(

    val weatherText: String,

    val isDayTime: Boolean,

    val temp: Double,

    val updateTime: Long
) : Serializable, IPreference


fun CurrentTemp.toPreferenceCurrentCondition(updateTime: Long): PreferenceCurrentCondition {
    return PreferenceCurrentCondition(
        weatherText,
        isDayTime,
        temp.metric.value,
        updateTime
    )
}