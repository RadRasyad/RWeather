package com.rad.rweather.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.ListForecast
import com.rad.rweather.core.domain.repository.IWeatherRepository

class WeatherInteractor(private val weatherRepository: IWeatherRepository): WeatherUseCase {
    override fun getForecast(lat: Double, lon: Double) = weatherRepository.getForecast(lat, lon)

    override fun getHourlyForecast() = weatherRepository.getHourlyForecast()

    override fun getDailyForecast() = weatherRepository.getDailyForecast()

}