package com.rad.rweather.core.domain.model


data class City(

    val id: Int?,
    val name: String?,
    val country: String?,
    val population: String?,
    val timeZone: String?,
    val sunrise: String?,
    val sunset: String?,
    val coord: Coord?
)
