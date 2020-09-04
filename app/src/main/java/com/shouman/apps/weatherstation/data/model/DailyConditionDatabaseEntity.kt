package com.shouman.apps.weatherstation.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shouman.apps.weatherstation.api.networkModel.DailyCondition

@Entity(tableName = "daily_condition_table")
data class DailyConditionDatabaseEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long,

    @ColumnInfo(name = "day_timestamp")
    val timeStamp: Long,

    @ColumnInfo(name = "max_temp")
    val maxTemp: Double,

    @ColumnInfo(name = "min_temp")
    val minTemp: Double,

    @ColumnInfo(name = "day_mode")
    val dayMode: String,

    @ColumnInfo(name = "night_mode")
    val nighMode: String
)


fun DailyCondition.toDailyConditionDatabaseEntity(): DailyConditionDatabaseEntity {
    return DailyConditionDatabaseEntity(
        -55,
        timestamp,
        temperature.max.value,
        temperature.min.value,
        dayStatus.status,
        nightStatus.status
    )
}

