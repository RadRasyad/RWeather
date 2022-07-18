package com.rad.rweather.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class WindResponse (

    @SerializedName("speed")
    val speed: Double?,

    @SerializedName("deg")
    val deg: Int,

    @SerializedName("gust")
    val gust: Double?
)
