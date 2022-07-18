package com.rad.rweather.core.di

import android.content.Context
import com.rad.rweather.core.data.WeatherRepository
import com.rad.rweather.core.data.source.local.LocalDataSource
import com.rad.rweather.core.data.source.local.room.ForecastDatabase
import com.rad.rweather.core.data.source.remote.RemoteDataSource
import com.rad.rweather.core.data.source.remote.network.ApiConfig
import com.rad.rweather.core.domain.repository.IWeatherRepository
import com.rad.rweather.core.domain.usecase.WeatherInteractor
import com.rad.rweather.core.domain.usecase.WeatherUseCase
import com.rad.rweather.core.utils.AppExecutors

object Injection {
    private fun provideRepository(context: Context): IWeatherRepository {
        val database = ForecastDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(ApiConfig.provideApiService())
        val localDataSource = LocalDataSource.getInstance(database.forecastDao())
        val appExecutors = AppExecutors()

        return WeatherRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

    fun provideTourismUseCase(context: Context): WeatherUseCase {
        val repository = provideRepository(context)
        return WeatherInteractor(repository)
    }
}