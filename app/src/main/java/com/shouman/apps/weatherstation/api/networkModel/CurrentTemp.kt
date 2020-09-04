package com.shouman.apps.weatherstation.api.networkModel

import com.squareup.moshi.Json


data class CurrentTemp(

    @Json(name = "WeatherText")
    val weatherText: String,

    @Json(name = "IsDayTime")
    val isDayTime: Boolean,

    @Json(name = "Temperature")
    val temp: Temperature

)


data class Temperature(
    @Json(name = "Metric")
    val metric: Metric,

    @Json(name = "Imperial")
    val imperial: Imperial
)


data class Metric(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String
)


data class Imperial(
    @Json(name = "Value")
    val value: Double,

    @Json(name = "Unit")
    val unit: String
) 
