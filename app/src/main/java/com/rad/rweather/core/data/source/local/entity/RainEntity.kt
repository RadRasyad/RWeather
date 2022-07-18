package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "rain_entity")
data class RainEntity (

    @ColumnInfo(name = "r3h")
    val r3h: Double?
)
