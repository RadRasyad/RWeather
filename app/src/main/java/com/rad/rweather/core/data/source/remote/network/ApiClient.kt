package com.rad.rweather.core.data.source.remote.network

import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("forecast")
    fun getForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") appId: String
    ): Call<ForecastResponse>

}