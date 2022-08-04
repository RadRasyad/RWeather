package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "clouds")
data class CloudsEntity (


    @ColumnInfo(name = "all")
    val all: Int?

)


