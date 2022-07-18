package com.rad.rweather.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RainResponse (

    @SerializedName("3h")
    val r3h: Double?
)
