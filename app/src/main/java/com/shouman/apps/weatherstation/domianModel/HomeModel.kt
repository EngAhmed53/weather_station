package com.shouman.apps.weatherstation.domianModel

import com.shouman.apps.weatherstation.data.model.DailyConditionDatabaseEntity
import com.shouman.apps.weatherstation.data.model.PreferenceCurrentCondition
import com.shouman.apps.weatherstation.data.model.PreferenceLocation
import com.shouman.apps.weatherstation.utils.DateUtils

data class HomeModel(

    val isDayTime: Boolean,

    val location: String,

    val date: String,

    val currentTemp: Int,

    val currentMode: String,

    val lastUpdated: Long,

    val maxTemp: Double,

    val minTemp: Double

)


fun toHmeModel(
    preferenceLocation: PreferenceLocation,
    preferenceCurrentCondition: PreferenceCurrentCondition,
    dailyConditionDatabaseEntity: DailyConditionDatabaseEntity
): HomeModel {
    return HomeModel(
        preferenceCurrentCondition.isDayTime,
        preferenceLocation.country + ", " + preferenceLocation.country,
        DateUtils.getDate(System.currentTimeMillis()),
        preferenceCurrentCondition.temp.toInt(),
        preferenceCurrentCondition.weatherText,
        preferenceCurrentCondition.updateTime,
        dailyConditionDatabaseEntity.maxTemp,
        dailyConditionDatabaseEntity.minTemp
    )
}
