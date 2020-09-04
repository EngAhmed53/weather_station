package com.shouman.apps.weatherstation.utils

import java.text.SimpleDateFormat
import java.util.*

class DateUtils {

    companion object {

        fun getDate(dateMilliSecond: Long?): String {
            dateMilliSecond?.let {
                val pattern = "dd MMMM YYYY"
                val date = Date(it)
                val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault());
                return simpleDateFormat.format(date)
            }
            return ""
        }
    }
}