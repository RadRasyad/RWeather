package com.rad.rweather.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rad.rweather.core.data.source.local.entity.ForecastEntity
import com.rad.rweather.core.data.source.local.entity.ListForecastEntity
import com.rad.rweather.core.domain.model.Forecast

@Dao
interface ForecastDao {

    @Query("SELECT * FROM forecast_entity")
    fun getForecast(): LiveData<ForecastEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertForecast(forecast: ForecastEntity)
}