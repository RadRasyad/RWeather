package com.rad.rweather.core.data.source.remote.response.forecast

import com.google.gson.annotations.SerializedName

data class WeatherItemResponse (

    @SerializedName("id")
    val id: Int?,

    @SerializedName("main")
    val mainWeather: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("icon")
    val icon: String?,
)
