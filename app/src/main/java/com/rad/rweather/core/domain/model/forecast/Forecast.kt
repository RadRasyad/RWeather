package com.rad.rweather.core.domain.model.forecast

import com.rad.rweather.core.domain.model.City

data class Forecast (

    val city: City?,
    val cnt: Int?,
    val cod: String?,
    val message: Double?,
    val list: List<ListForecast>?
)