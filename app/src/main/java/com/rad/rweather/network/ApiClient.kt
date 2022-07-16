package com.rad.rweather.network

import com.rad.rweather.BuildConfig
import com.rad.rweather.model.remote.response.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("forecast")
    fun getHourlyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String,
        @Query("cnt") cnt: Int
        ): Call<ForecastResponse>

    @GET("forecast?lat={lat}&lon={lon}&appid={appid}")
    fun getDailyForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ): Call<ForecastResponse>
}