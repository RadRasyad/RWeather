package com.rad.rweather.core.data

import androidx.lifecycle.LiveData
import com.rad.rweather.core.data.source.local.LocalDataSource
import com.rad.rweather.core.data.source.remote.RemoteDataSource
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.ForecastResponse
import com.rad.rweather.core.domain.model.Forecast
import com.rad.rweather.core.domain.model.ListForecast
import com.rad.rweather.core.domain.repository.IWeatherRepository
import com.rad.rweather.core.utils.AppExecutors

class WeatherRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWeatherRepository{

    companion object {
        @Volatile
        private var instance: WeatherRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): WeatherRepository =
            instance ?: synchronized(this) {
                instance ?: WeatherRepository(remoteData, localData, appExecutors)
            }
    }

    override fun getHourlyForecast(): LiveData<Resource<List<ListForecast>>> {
        TODO("Not yet implemented")
    }

    override fun getDailyForecast(): LiveData<Resource<List<ListForecast>>> {
        TODO("Not yet implemented")
    }

    override fun getForecast(lat: Double, lon: Double): LiveData<Resource<Forecast>> =
        object : NetworkBoundResource<Forecast, ForecastResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<Forecast> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: Forecast?): Boolean {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<ForecastResponse>> {
                TODO("Not yet implemented")
            }

            override fun saveCallResult(data: ForecastResponse) {
                TODO("Not yet implemented")
            }

        }.asLiveData()
}