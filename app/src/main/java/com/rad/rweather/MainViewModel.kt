package com.rad.rweather

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rad.rweather.core.data.source.remote.RemoteDataSource
import com.rad.rweather.core.data.source.remote.network.ApiClient
import com.rad.rweather.core.data.source.remote.network.ApiConfig
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.domain.usecase.WeatherUseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {

    fun forecast(lat: Double, lon: Double) = weatherUseCase.getForecast(lat, lon)

}