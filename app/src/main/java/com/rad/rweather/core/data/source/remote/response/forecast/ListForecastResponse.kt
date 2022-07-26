package com.rad.rweather.core.data.source.remote.response.forecast

import com.google.gson.annotations.SerializedName
import com.rad.rweather.core.data.source.remote.response.CloudsResponse
import com.rad.rweather.core.data.source.remote.response.RainResponse
import com.rad.rweather.core.data.source.remote.response.SysResponse
import com.rad.rweather.core.data.source.remote.response.WindResponse

data class ListForecastResponse(

    @SerializedName("dt")
    val date: Long?,

    @SerializedName("main")
    val main: MainWeatherResponse?,

    @SerializedName("weather")
    val weather: List<WeatherItemResponse>?,

    @SerializedName("clouds")
    val clouds: CloudsResponse?,

    @SerializedName("wind")
    val wind: WindResponse?,

    @SerializedName("visibility")
    val visibility: Int?,

    @SerializedName("pop")
    val pop: Double?,

    @SerializedName("rain")
    val rain: RainResponse?,

    @SerializedName("sys")
    val sys: SysResponse?,

    @SerializedName("dt_txt")
    val dateText: String?,
)
