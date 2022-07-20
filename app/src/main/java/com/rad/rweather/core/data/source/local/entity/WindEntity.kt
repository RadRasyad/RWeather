package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "wind_entity")
data class WindEntity (

    @ColumnInfo(name = "speed")
    val speed: Double?,

    @ColumnInfo(name = "deg")
    val deg: Int?,

    @ColumnInfo(name = "gust")
    val gust: Double?
)
