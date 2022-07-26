package com.rad.rweather.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rad.rweather.core.data.source.local.entity.currentforecast.CurrentWeatherEntity
import com.rad.rweather.core.data.source.local.entity.forecast.ForecastEntity

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast_entity")
    fun getForecast(): LiveData<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: ForecastEntity)

    @Query("SELECT * FROM current_weather_entity")
    fun getCurrentForecast(): LiveData<CurrentWeatherEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCurrentForecast(forecast: CurrentWeatherEntity)
}