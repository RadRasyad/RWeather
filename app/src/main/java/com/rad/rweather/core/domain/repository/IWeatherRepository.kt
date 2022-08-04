package com.rad.rweather.core.domain.repository

import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {

    fun getForecast(lat: Double, lon: Double): Flow<Resource<Forecast>>

    fun getCurrentForecast(lat: Double, lon: Double): Flow<Resource<CurrentWeather>>
}