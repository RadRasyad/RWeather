package com.rad.rweather.core.domain.model.forecast

import com.google.gson.annotations.SerializedName

data class MainWeather (

    val temp : Double?,
    val feelsLike : Double?,
    val tempMin : Double?,
    val tempMax: Double?,
    val pressure: Int?,
    val seaLevel: Int?,
    val groundLevel: Int?,
    val humidity: Int?,
    val tempKf: Double?

)
