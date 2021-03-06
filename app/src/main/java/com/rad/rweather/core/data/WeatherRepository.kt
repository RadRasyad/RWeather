package com.rad.rweather.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.rad.rweather.core.data.source.local.LocalDataSource
import com.rad.rweather.core.data.source.remote.RemoteDataSource
import com.rad.rweather.core.data.source.remote.network.ApiResponse
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
import com.rad.rweather.core.data.source.remote.response.forecast.ForecastResponse
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.forecast.Forecast
import com.rad.rweather.core.domain.repository.IWeatherRepository
import com.rad.rweather.core.utils.AppExecutors
import com.rad.rweather.core.utils.datamapper.MapperEntityToDomain
import com.rad.rweather.core.utils.datamapper.MapperResponseToEntity

class WeatherRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWeatherRepository {

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

    override fun getForecast(lat: Double, lon: Double): LiveData<Resource<Forecast>> =
        object : NetworkBoundResource<Forecast, ForecastResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<Forecast> {
                val data = localDataSource.getForecast()
                return Transformations.map(data) {
                    MapperEntityToDomain.mapForecastEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Forecast?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<ForecastResponse>> =
                remoteDataSource.getForecast(lat, lon)

            override fun saveCallResult(data: ForecastResponse) {
                val forecast = MapperResponseToEntity.mapForecastResponseToEntity(data)

                localDataSource.insertForecast(forecast)
            }

        }.asLiveData()

    override fun getCurrentForecast(lat: Double, lon: Double): LiveData<Resource<CurrentWeather>> =
        object : NetworkBoundResource<CurrentWeather, CurrentWeatherResponse>(appExecutors) {

            override fun loadFromDB(): LiveData<CurrentWeather> {
                val data = localDataSource.getCurrentForecast()
                return Transformations.map(data) {
                    MapperEntityToDomain.mapCurrentForecastEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: CurrentWeather?): Boolean = true

            override fun createCall(): LiveData<ApiResponse<CurrentWeatherResponse>> =
                remoteDataSource.getCurrentForecast(lat, lon)

            override fun saveCallResult(data: CurrentWeatherResponse) {
                val forecast = MapperResponseToEntity.mapCurrentForecastResponseToEntity(data)

                localDataSource.insertCurrentForecast(forecast)
            }

        }.asLiveData()

}