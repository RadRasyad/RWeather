package com.rad.rweather.core.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rad.rweather.BuildConfig
import com.rad.rweather.core.data.source.remote.network.ApiClient
import com.rad.rweather.core.data.source.remote.response.forecast.ForecastResponse
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
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

    private val units = "metric"

    private val appID: String
        get() = BuildConfig.API_KEY

    fun getForecast(lat: Double, lon: Double): LiveData<ApiResponse<ForecastResponse>> {
        val resultData = MutableLiveData<ApiResponse<ForecastResponse>>()

        service.getForecast(lat, lon, appID, units).enqueue(object :
            Callback<ForecastResponse> {
            override fun onResponse(
                call: Call<ForecastResponse>,
                response: Response<ForecastResponse>
            ) {
                val data = response.body()
                resultData.value = if (data!=null) ApiResponse.Success(data) else ApiResponse.Empty

            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("Response Failed", t.message.toString())
            }

        })

        return resultData
    }

    fun getCurrentForecast(lat: Double, lon: Double): LiveData<ApiResponse<CurrentWeatherResponse>> {
        val resultData = MutableLiveData<ApiResponse<CurrentWeatherResponse>>()

        service.getCurrentForecast(lat, lon, appID, units).enqueue(object :
            Callback<CurrentWeatherResponse> {
            override fun onResponse(
                call: Call<CurrentWeatherResponse>,
                response: Response<CurrentWeatherResponse>
            ) {
                val data = response.body()
                resultData.value = if (data!=null) ApiResponse.Success(data) else ApiResponse.Empty
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                resultData.value = ApiResponse.Error(t.message.toString())
                Log.d("Response Failed", t.message.toString())
            }
        })

        return resultData
    }
}