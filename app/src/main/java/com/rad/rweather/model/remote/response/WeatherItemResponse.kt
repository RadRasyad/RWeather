package com.rad.rweather.model.remote.response

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
