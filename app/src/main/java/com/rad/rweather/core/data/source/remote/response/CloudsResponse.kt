package com.rad.rweather.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CloudsResponse (

    @SerializedName("all")
    val all: Int?
)
