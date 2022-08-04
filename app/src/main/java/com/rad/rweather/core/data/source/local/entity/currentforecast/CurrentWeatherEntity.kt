package com.rad.rweather.core.data.source.local.entity.currentforecast

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rad.rweather.core.data.source.local.entity.*
import com.rad.rweather.core.data.source.local.entity.forecast.MainWeatherEntity

@Entity(tableName = "current_weather_entity")
data class CurrentWeatherEntity(

    @PrimaryKey
    @ColumnInfo(name = "crn_id")
    val id: Int?,

    @ColumnInfo(name = "crn_cod")
    val cod: Int?,

    @ColumnInfo(name = "base")
    val base: String?,

    @ColumnInfo(name = "crn_name")
    val name: String?,

    @ColumnInfo(name = "crn_visibility")
    val visibility: String?,

    @Embedded
    val coord: CoordEntity?,

    @ColumnInfo(name = "crn_date")
    val date: Int?,

    @ColumnInfo(name = "crn_timezone")
    val timezone: Int?,

    @Embedded
    val main: MainWeatherEntity?,

    @Embedded
    val wind: WindEntity?,

    @Embedded
    val clouds: CloudsEntity?,

    @Embedded
    val rain: RainEntity?,

    @ColumnInfo(name = "weather_data")
    val weather: List<WeatherItemEntity>?,

    @Embedded
    val sys: CurrentWeatherSysEntity?
)
