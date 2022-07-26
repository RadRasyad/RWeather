package com.rad.rweather.core.data.source.remote.response.forecast

import com.google.gson.annotations.SerializedName

data class MainWeatherResponse (

    @SerializedName("temp")
    val temp : Double?,

    @SerializedName("feels_like")
    val feelsLike : Double?,

    @SerializedName("temp_min")
    val tempMin : Double?,

    @SerializedName("temp_max")
    val tempMax: Double?,

    @SerializedName("pressure")
    val pressure: Int?,

    @SerializedName("sea_level")
    val seaLevel: Int?,

    @SerializedName("grnd_level")
    val groundLevel: Int?,

    @SerializedName("humidity")
    val humidity: Int?,

    @SerializedName("temp_kf")
    val tempKf: Double?

)
