package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "coord_entity")
data class CoordEntity (

    @ColumnInfo(name = "lat")
    val lat: Double?,

    @ColumnInfo(name = "lon")
    val lon: Double?,
)
