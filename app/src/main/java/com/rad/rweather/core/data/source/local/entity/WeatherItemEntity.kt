package com.rad.rweather.core.data.source.local.entity

import androidx.room.Entity
import com.squareup.moshi.Json

@Entity(tableName = "weather_item_entity")
data class WeatherItemEntity (

    @Json(name = "id")
    val id: Int?,

    @Json(name = "main")
    val mainWeather: String?,

    @Json(name = "description_weather")
    val description: String?,

    @Json(name = "description")
    val icon: String?
)
