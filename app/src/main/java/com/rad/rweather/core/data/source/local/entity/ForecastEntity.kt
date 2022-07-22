package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_entity")
data class ForecastEntity (

    @Embedded
    var city: CityEntity?,

    @ColumnInfo(name = "cnt")
    var cnt: Int?,

    @PrimaryKey
    @ColumnInfo(name = "cod")
    var cod: String,

    @ColumnInfo(name = "message")
    var message: Double?,

    @ColumnInfo(name = "list_forecast")
    var list: List<ListForecastEntity>?
)