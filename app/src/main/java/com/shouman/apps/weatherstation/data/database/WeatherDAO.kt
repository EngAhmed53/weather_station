package com.shouman.apps.weatherstation.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shouman.apps.weatherstation.data.model.DailyConditionDatabaseEntity
import com.shouman.apps.weatherstation.data.model.HourlyConditionDatabaseEntity

@Dao
interface WeatherDAO {

    @Query("SELECT * FROM daily_condition_table")
    fun getNext5DaysCondition(): LiveData<List<DailyConditionDatabaseEntity>>

    @Query("SELECT * FROM hourly_condition_table")
    fun getCurrentDayNext12HourCondition(): LiveData<List<HourlyConditionDatabaseEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew5DaysCondition(vararg dailyCondition: DailyConditionDatabaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNew12HourCondition(vararg hourlyCondition: HourlyConditionDatabaseEntity)


    @Query("DELETE FROM daily_condition_table")
    fun deleteAllDailyRecords()

    @Query("DELETE FROM hourly_condition_table")
    fun deleteAllHourlyRecords()

}