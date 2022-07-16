package com.rad.rweather.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {

    val interceptor = HttpLoggingInterceptor()
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    fun create(): ApiClient {

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiClient::class.java)
    }
}