package com.rad.rweather.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast
import kotlinx.coroutines.flow.Flow

interface WeatherUseCase {

    fun getForecast(lat: Double, lon: Double): Flow<Resource<Forecast>>

    fun getCurrentForecast(lat: Double, lon: Double): Flow<Resource<CurrentWeather>>
}