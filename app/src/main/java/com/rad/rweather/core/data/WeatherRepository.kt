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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IWeatherRepository {

    override fun getForecast(lat: Double, lon: Double): Flow<Resource<Forecast>> =
        object : NetworkBoundResource<Forecast, ForecastResponse>() {

            override fun loadFromDB(): Flow<Forecast> {
                return localDataSource.getForecast().map {
                    MapperEntityToDomain.mapForecastEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: Forecast?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<ForecastResponse>> =
                remoteDataSource.getForecast(lat, lon)

            override suspend fun saveCallResult(data: ForecastResponse) {
                val forecast = MapperResponseToEntity.mapForecastResponseToEntity(data)
                localDataSource.insertForecast(forecast)
            }
        }.asFlow()

    override fun getCurrentForecast(lat: Double, lon: Double): Flow<Resource<CurrentWeather>> =
        object : NetworkBoundResource<CurrentWeather, CurrentWeatherResponse>() {

            override fun loadFromDB(): Flow<CurrentWeather> {
                return localDataSource.getCurrentForecast().map {
                    MapperEntityToDomain.mapCurrentForecastEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: CurrentWeather?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<CurrentWeatherResponse>> =
                remoteDataSource.getCurrentForecast(lat, lon)

            override suspend fun saveCallResult(data: CurrentWeatherResponse) {
                val forecast = MapperResponseToEntity.mapCurrentForecastResponseToEntity(data)

                localDataSource.insertCurrentForecast(forecast)
            }
        }.asFlow()

}