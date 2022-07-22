package com.rad.rweather.core.domain.usecase


import com.rad.rweather.core.domain.repository.IWeatherRepository

class WeatherInteractor(private val weatherRepository: IWeatherRepository): WeatherUseCase {

    override fun getForecast(lat: Double, lon: Double) = weatherRepository.getForecast(lat, lon)

}