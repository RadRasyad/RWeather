package com.rad.rweather.core.data.source.local

import com.rad.rweather.core.data.source.local.room.ForecastDao

class LocalDataSource private constructor(private val forecastDao: ForecastDao) {

    companion object {
        private var instance: LocalDataSource? = null

        fun getInstance(forecastDao: ForecastDao): LocalDataSource =
            instance ?: synchronized(this) {
                instance ?: LocalDataSource(forecastDao)
            }
    }
}