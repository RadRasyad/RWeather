package com.rad.rweather.core.domain.model.currentforecast

import com.google.gson.annotations.SerializedName


data class CurrentWeatherSys (

    @SerializedName("type")
    val type: Int?,

    @SerializedName("id")
    val idSys: Int?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("sunrise")
    val sunrise: Int?,

    @SerializedName("sunset")
    val sunset: Int?
)
