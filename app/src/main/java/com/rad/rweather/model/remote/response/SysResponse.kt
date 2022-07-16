package com.rad.rweather.model.remote.response

import com.google.gson.annotations.SerializedName

data class SysResponse (

    @SerializedName("pod")
    val pod: String?
)
