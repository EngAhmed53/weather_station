package com.shouman.apps.weatherstation.data.model

import com.shouman.apps.weatherstation.api.networkModel.Location
import java.io.Serializable


data class PreferenceLocation(
    val key: Long,

    val localizedName: String,

    val country: String,

    val city: String
) : Serializable


fun Location.toPreferenceLocation(): PreferenceLocation {
    return PreferenceLocation(
        key,
        localizedName,
        country.localizedName,
        city.localizedName
    )
}