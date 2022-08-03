package com.rad.rweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rad.rweather.core.domain.usecase.WeatherUseCase


class MainViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    fun forecast(lat: Double, lon: Double) = weatherUseCase.getForecast(lat, lon).asLiveData()

    fun currentForecast(lat: Double, lon: Double) = weatherUseCase.getCurrentForecast(lat, lon).asLiveData()

}