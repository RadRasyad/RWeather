package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListForecastEntity(

    @Json(name = "date")
    val date: Long?,

    @Json(name = "main")
    val main: MainWeatherEntity?,

    @Json(name = "weather")
    val weather: List<WeatherItemEntity>?,

    @Json(name = "cloud")
    val clouds: CloudsEntity?,

    @Json(name = "wind")
    val wind: WindEntity?,

    @Json(name = "visibility")
    val visibility: Int?,

    @Json(name = "pop")
    val pop: Double?,

    @Json(name = "rain")
    val rain: RainEntity?,

    @Json(name = "sys")
    val sys: SysEntity?,

    @Json(name = "dateTxt")
    val dateText: String?,
)
