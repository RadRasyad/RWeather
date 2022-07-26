package com.rad.rweather.core.data.source.remote.response.currentforecast

import com.google.gson.annotations.SerializedName
import com.rad.rweather.core.data.source.remote.response.RainResponse
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
    val coord: Coord?,

    @SerializedName("dt")
    val date: Int?,

    @SerializedName("timezone")
    val timezone: Int?,

    @SerializedName("main")
    val main: List<MainWeather>?,

    @SerializedName("wind")
    val wind: Wind?,

    @SerializedName("clouds")
    val clouds: Clouds?,

    @SerializedName("rain")
    val rain: RainResponse?,

    @SerializedName("sys")
    val sys: CurrentWeatherSysResponse?
)
