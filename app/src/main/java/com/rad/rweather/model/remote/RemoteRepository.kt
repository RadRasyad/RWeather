package com.rad.rweather.model.remote

import android.util.Log
import com.rad.rweather.BuildConfig
import com.rad.rweather.model.remote.response.ForecastResponse
import com.rad.rweather.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteRepository {

    private val apiConfig = ApiConfig
    private val appID: String
        get() = BuildConfig.API_KEY

    fun getHourlyForecast(lat: Double, lon: Double) {

        apiConfig.create().getDailyForecast(-6.966667, 110.416664, appID).enqueue(object :
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