package com.rad.rweather.model.remote.response

import com.google.gson.annotations.SerializedName

data class CloudsResponse (

    @SerializedName("all")
    val all: Int?
)
