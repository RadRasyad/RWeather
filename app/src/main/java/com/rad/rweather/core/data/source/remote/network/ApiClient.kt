package com.rad.rweather.core.data.source.remote.network

import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
import com.rad.rweather.core.data.source.remote.response.forecast.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("forecast")
    suspend fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): ForecastResponse

    @GET("weather")
    suspend fun getCurrentForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("units") units: String
    ): CurrentWeatherResponse

}