package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "list_forecast_entity")
data class ListForecastEntity(


    @ColumnInfo(name = "date")
    val date: Long?,

    @Embedded
    val main: MainWeatherEntity?,

    @ColumnInfo(name = "weather_item")
    val weather: List<WeatherItemEntity>?,

    @Embedded
    val clouds: CloudsEntity?,

    @Embedded
    val wind: WindEntity?,

    @ColumnInfo(name = "visibility")
    val visibility: Int?,

    @ColumnInfo(name = "pop")
    val pop: Double?,

    @Embedded
    val rain: RainEntity?,

    @Embedded
    val sys: SysEntity?,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "datetext")
    val dateText: String?,
)
