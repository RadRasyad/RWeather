package com.rad.rweather.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import com.rad.rweather.BuildConfig
import com.rad.rweather.core.data.source.remote.network.ApiClient
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.data.source.remote.network.ApiConfig
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val service: ApiClient) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(service: ApiClient): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(service)
            }
    }

    private val appID: String
        get() = BuildConfig.API_KEY

    fun getForecast(lat: Double, lon: Double) {

        service.getForecast(lat, lon, appID).enqueue(object :
            Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                Log.d("Response Success", response.body().toString())
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                Log.d("Response Failed", t.message.toString())
            }

        })

    }
}