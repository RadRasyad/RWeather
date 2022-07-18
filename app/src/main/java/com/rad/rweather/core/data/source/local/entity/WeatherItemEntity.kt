package com.rad.rweather.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_item_entity")
data class WeatherItemEntity (

    @ColumnInfo(name = "id_weather")
    val id: Int?,

    @ColumnInfo(name = "main_weather")
    val mainWeather: String?,

    @ColumnInfo(name = "description")
    val description: String?,

    @ColumnInfo(name = "description")
    val icon: String?
)
