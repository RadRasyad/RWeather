package com.rad.rweather.core.data.source.local.entity.currentforecast

import com.rad.rweather.core.domain.model.*
import com.rad.rweather.core.domain.model.forecast.MainWeather

data class CurrentWeatherEntity(

    val id: Int?,
    val cod: Int?,
    val base: String?,
    val name: String?,
    val visibility: String?,
    val coord: Coord?,
    val date: Int?,
    val timezone: Int?,
    val main: List<MainWeather>?,
    val wind: Wind?,
    val clouds: Clouds?,
    val rain: Rain?,
    val sys: CurrentWeatherSysEntity?
)
