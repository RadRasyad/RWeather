package com.rad.rweather.core.data.source.local.entity.currentforecast

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "crn_sys_entity")
data class CurrentWeatherSysEntity (

    @ColumnInfo(name = "crn_type")
    val type: Int?,

    @ColumnInfo(name = "crn_idsys")
    val idSys: Int?,

    @ColumnInfo(name = "crn_country")
    val country: String?,

    @ColumnInfo(name = "crn_sunrise")
    val sunrise: Int?,

    @ColumnInfo(name = "crn_sunset")
    val sunset: Int?
)
