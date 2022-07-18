package com.rad.rweather.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sys_entity")
data class SysEntity (

    @ColumnInfo(name = "pod")
    val pod: String?
)
