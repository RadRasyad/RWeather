package com.rad.rweather.core.domain.repository

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast

interface IWeatherRepository {

    fun getForecast(lat: Double, lon: Double): LiveData<Resource<Forecast>>

    fun getCurrentForecast(lat: Double, lon: Double): LiveData<Resource<CurrentWeather>>
}