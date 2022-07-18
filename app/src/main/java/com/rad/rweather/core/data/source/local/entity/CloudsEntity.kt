package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import com.google.gson.annotations.SerializedName

@Entity(tableName = "clouds")
data class CloudsEntity (


    @ColumnInfo(name = "all")
    val all: Int?

)


