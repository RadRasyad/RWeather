package com.rad.rweather.core.domain.usecase

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.forecast.Forecast

interface WeatherUseCase {

    fun getForecast(lat: Double, lon: Double): LiveData<Resource<Forecast>>

}