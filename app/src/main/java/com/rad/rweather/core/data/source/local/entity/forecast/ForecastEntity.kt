package com.rad.rweather.core.data.source.local.entity.forecast

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rad.rweather.core.data.source.local.entity.CityEntity

@Entity(tableName = "forecast_entity")
data class ForecastEntity (

    @Embedded
    var city: CityEntity?,

    @ColumnInfo(name = "cnt")
    var cnt: Int?,

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cod")
    var cod: String,

    @ColumnInfo(name = "message")
    var message: Double?,

    @ColumnInfo(name = "list_forecast")
    var list: List<ListForecastEntity>?
)