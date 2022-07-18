package com.rad.rweather.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CityResponse(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("country")
    val country: String?,

    @SerializedName("population")
    val population: String?,

    @SerializedName("timezone")
    val timeZone: String?,

    @SerializedName("sunrise")
    val sunrise: String?,

    @SerializedName("sunset")
    val sunset: String?,

    @SerializedName("coord")
    val coord: CoordResponse?
)
