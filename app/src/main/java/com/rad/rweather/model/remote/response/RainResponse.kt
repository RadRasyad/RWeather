package com.rad.rweather.model.remote.response

import com.google.gson.annotations.SerializedName

data class RainResponse (

    @SerializedName("3h")
    val r3h: Double?
)
