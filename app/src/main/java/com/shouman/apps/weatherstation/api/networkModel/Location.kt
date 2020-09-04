package com.shouman.apps.weatherstation.api.networkModel

import com.squareup.moshi.Json
import java.io.Serializable

data class Location(
    @Json(name = "Key")
    val key: Long,

    @Json(name = "LocalizedName")
    val localizedName: String,

    @Json(name = "Country")
    val country: Country,

    @Json(name = "AdministrativeArea")
    val city: City,

    @Json(name = "GeoPosition")
    val geoPosition: GeoPosition

) 

data class Country(
    @Json(name = "LocalizedName")
    val localizedName: String,

    @Json(name = "EnglishName")
    val englishName: String

) 

data class City(

    @Json(name = "LocalizedName")
    val localizedName: String,

    @Json(name = "EnglishName")
    val englishName: String,

    ) 


data class GeoPosition(
    @Json(name = "Latitude")
    val lat: Double,
    @Json(name = "Longitude")
    val long: Double
) 