package com.rad.rweather.core.data.source.remote.response.currentforecast

import com.google.gson.annotations.SerializedName
import com.rad.rweather.core.data.source.local.entity.WeatherItemEntity
import com.rad.rweather.core.data.source.remote.response.CloudsResponse
import com.rad.rweather.core.data.source.remote.response.CoordResponse
import com.rad.rweather.core.data.source.remote.response.RainResponse
import com.rad.rweather.core.data.source.remote.response.WindResponse
import com.rad.rweather.core.data.source.remote.response.forecast.MainWeatherResponse
import com.rad.rweather.core.data.source.remote.response.forecast.WeatherItemResponse
import com.rad.rweather.core.domain.model.*
import com.rad.rweather.core.domain.model.forecast.MainWeather

data class CurrentWeatherResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("cod")
    val cod: Int?,

    @SerializedName("base")
    val base: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("visibility")
    val visibility: String?,

    @SerializedName("coord")
    val coord: CoordResponse?,

    @SerializedName("dt")
    val date: Int?,

    @SerializedName("timezone")
    val timezone: Int?,

    @SerializedName("main")
    val main: MainWeatherResponse?,

    @SerializedName("wind")
    val wind: WindResponse?,

    @SerializedName("clouds")
    val clouds: CloudsResponse?,

    @SerializedName("rain")
    val rain: RainResponse?,

    @SerializedName("weather")
    val weather: List<WeatherItemResponse>?,

    @SerializedName("sys")
    val sys: CurrentWeatherSysResponse?
)
