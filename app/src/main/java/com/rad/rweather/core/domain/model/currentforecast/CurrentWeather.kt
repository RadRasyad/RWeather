package com.rad.rweather.core.domain.model.currentforecast

import com.rad.rweather.core.data.source.remote.response.forecast.WeatherItemResponse
import com.rad.rweather.core.domain.model.*
import com.rad.rweather.core.domain.model.forecast.MainWeather
import com.rad.rweather.core.domain.model.forecast.WeatherItem

data class CurrentWeather(

    val id: Int?,
    val cod: Int?,
    val base: String?,
    val name: String?,
    val visibility: String?,
    val coord: Coord?,
    val date: Int?,
    val timezone: Int?,
    val main: MainWeather?,
    val wind: Wind?,
    val clouds: Clouds?,
    val rain: Rain?,
    val weather: List<WeatherItem>?,
    val sys: CurrentWeatherSys?
)
