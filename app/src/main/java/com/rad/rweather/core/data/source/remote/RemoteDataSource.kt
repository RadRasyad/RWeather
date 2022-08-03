package com.rad.rweather.core.data.source.remote

import com.rad.rweather.BuildConfig
import com.rad.rweather.core.data.source.remote.network.ApiClient
import com.rad.rweather.core.data.source.remote.response.forecast.ForecastResponse
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val service: ApiClient) {

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

    suspend fun getForecast(lat: Double, lon: Double): Flow<ApiResponse<ForecastResponse>> {

        return flow {
            try {
                val response = service.getForecast(lat, lon, appID, units)

                if (response.list?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }

            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCurrentForecast(lat: Double, lon: Double): Flow<ApiResponse<CurrentWeatherResponse>> {
        return flow {
            try {
                val response = service.getCurrentForecast(lat, lon, appID, units)

                if (response !=  null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

}