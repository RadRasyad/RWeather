package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_entity")
data class ForecastEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @Embedded
    val city: CityEntity?,

    @ColumnInfo(name = "cnt")
    val cnt: Int?,

    @ColumnInfo(name = "cod")
    val cod: String?,

    @ColumnInfo(name = "message")
    val message: Double?,

    @ColumnInfo(name = "list_forecast")
    val list: List<ListForecastEntity>?
)