package com.shouman.apps.weatherstation.api.networkModel

import com.squareup.moshi.Json

data class HourlyCondition(

    @Json(name = "EpochDateTime")
    val timestamp: Long,

    @Json(name = "IconPhrase")
    val weatherStatus: String,

    @Json(name = "IsDaylight")
    val isDayLight: Boolean,

    @Json(name = "Temperature")
    val temp: Metric

)