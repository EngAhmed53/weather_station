package com.shouman.apps.weatherstation.api.networkModel

import com.squareup.moshi.Json
import java.io.Serializable


data class DailyCondition5Days(
    @Json(name = "DailyForecasts")
    val dailyCondition5DaysList: List<DailyCondition>
)


data class DailyCondition(

    @Json(name = "EpochDate")
    val timestamp: Long,

    @Json(name = "Temperature")
    val temperature: DailyTemperature,

    @Json(name = "Day")
    val dayStatus: TimeStatus,

    @Json(name = "Night")
    val nightStatus: TimeStatus

)

data class TimeStatus(
    @Json(name = "IconPhrase")
    val status: String
)


data class DailyTemperature(
    @Json(name = "Minimum")
    val min: Metric,

    @Json(name = "Maximum")
    val max: Metric
) : Serializable
