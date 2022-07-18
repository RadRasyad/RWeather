package com.rad.rweather.core.domain.model

data class Forecast (

    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val message: Double?,
    val list: List<ListForecast>?
)