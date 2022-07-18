package com.rad.rweather.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse (

    @SerializedName("city")
    val city: CityResponse?,

    @SerializedName("cnt")
    val cnt: Int?,

    @SerializedName("cod")
    val cod: String?,

    @SerializedName("message")
    val message: Double?,

    @SerializedName("list")
    val list: List<ListForecastResponse>?
)