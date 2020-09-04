package com.shouman.apps.weatherstation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shouman.apps.weatherstation.api.networkModel.HourlyCondition

@Entity(tableName = "hourly_condition_table")
data class HourlyConditionDatabaseEntity(

    @PrimaryKey
    val id: Long,

    @ColumnInfo(name = "hour_timestamp")
    val timeStamp: Long,

    @ColumnInfo(name = "weather_mode")
    val weatherMode: String,

    @ColumnInfo(name = "day_light")
    val isDayLight: Boolean,

    @ColumnInfo(name = "metric_temp")
    val temp: Double
)


fun HourlyCondition.toHourlyConditionDatabaseEntity(): HourlyConditionDatabaseEntity {
    return HourlyConditionDatabaseEntity(
        -555,
        timestamp,
        weatherStatus,
        isDayLight,
        temp.value
    )
}
