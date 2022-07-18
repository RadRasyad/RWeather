package com.rad.rweather.core.domain.repository

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.Resource
import com.rad.rweather.core.domain.model.ListForecast

interface IWeatherRepository {

    fun getHourlyForecast(): LiveData<Resource<List<ListForecast>>>

    fun getDailyForecast(): LiveData<Resource<List<ListForecast>>>

    fun getForecast(lat: Double, lon: Double)
}