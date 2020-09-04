package com.shouman.apps.weatherstation.utils

object TempUtils {

    fun getDegreeText(temp: Double?): String {
        temp?.let {
            return "$it°"
        }
        return ""
    }

    fun getMaxDegreeText(temp: Double?): String {
        temp?.let {
            return "$it° ↑"
        }
        return ""
    }

    fun getMinDegreeText(temp: Double?): String {
        temp?.let {
            return "$it° ↓"
        }
        return ""
    }


}