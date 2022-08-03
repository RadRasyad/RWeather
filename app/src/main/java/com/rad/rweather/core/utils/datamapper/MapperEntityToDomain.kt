package com.rad.rweather.core.utils.datamapper

import com.rad.rweather.core.data.source.local.entity.*
import com.rad.rweather.core.data.source.local.entity.forecast.ForecastEntity
import com.rad.rweather.core.data.source.local.entity.forecast.ListForecastEntity
import com.rad.rweather.core.data.source.local.entity.forecast.MainWeatherEntity
import com.rad.rweather.core.data.source.local.entity.WeatherItemEntity
import com.rad.rweather.core.data.source.local.entity.currentforecast.CurrentWeatherEntity
import com.rad.rweather.core.data.source.local.entity.currentforecast.CurrentWeatherSysEntity
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
import com.rad.rweather.core.domain.model.*
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeather
import com.rad.rweather.core.domain.model.currentforecast.CurrentWeatherSys
import com.rad.rweather.core.domain.model.forecast.*

object MapperEntityToDomain {

    fun mapForecastEntityToDomain(data: ForecastEntity?): Forecast {

        return Forecast(
            city = data?.city?.let { mapCityEntityToCity(it) },
            cod = data?.cod,
            cnt = data?.cnt,
            message = data?.message,
            list = data?.list?.let { mapListForecastEntityToListForecast(it) }
        )
    }

    fun mapCurrentForecastEntityToDomain(data: CurrentWeatherEntity?): CurrentWeather {

        return CurrentWeather(
            id = data?.id,
            cod = data?.cod,
            base = data?.base,
            name = data?.name,
            visibility = data?.visibility,
            coord = data?.coord?.let { mapCoordEntityToCoord(it) },
            date = data?.date,
            timezone = data?.timezone,
            main = data?.main?.let { mapMainEntityToMain(it) },
            wind = data?.wind?.let { mapWindEntityToWind(it) },
            rain = data?.rain?.let { mapRainEntityToRain(it) },
            sys = data?.sys?.let { mapSysEntityToSys2(it) },
            clouds = data?.clouds?.let { mapCloundEntityToCloud(it) },
            weather = data?.weather?.let { mapWeatherItemEntityToWeatherItem(it) }
        )
    }

    private fun mapCityEntityToCity(data: CityEntity?): City? {

        return City(
            id = data?.cityId,
            name = data?.name,
            country = data?.country,
            population = data?.population,
            timeZone = data?.timeZone,
            sunrise = data?.sunrise,
            sunset = data?.sunset,
            coord = data?.coord?.let { mapCoordEntityToCoord(it) }

        )
    }

    private fun mapCoordEntityToCoord(data: CoordEntity): Coord {

        return Coord(
            lat = data.lat,
            lon = data.lon
        )
    }

    private fun mapListForecastEntityToListForecast(data: List<ListForecastEntity>): List<ListForecast> {

        val forecastList = ArrayList<ListForecast>()
        data.map {
            val weather = ListForecast(
                date = it.date,
                main = it.main?.let { it1 -> mapMainEntityToMain(it1) },
                weather = it.weather?.let { it1 -> mapWeatherItemEntityToWeatherItem(it1) },
                clouds = it.clouds?.let { it1 -> mapCloundEntityToCloud(it1) },
                wind = it.wind?.let { it1 -> mapWindEntityToWind(it1) },
                visibility = it.visibility,
                pop = it.pop,
                rain = it.rain?.let { it1 -> mapRainEntityToRain(it1) },
                sys = it.sys?.let { it1 -> mapSysEntityToSys(it1) },
                dateText = it.dateText
            )
            forecastList.add(weather)
        }
        return forecastList
    }

    private fun mapMainEntityToMain(data: MainWeatherEntity): MainWeather {

        return MainWeather(
            temp = data.temp,
            feelsLike = data.feelsLike,
            tempMin = data.tempMin,
            tempMax = data.tempMax,
            pressure = data.pressure,
            seaLevel = data.seaLevel,
            groundLevel = data.groundLevel,
            humidity = data.humidity,
            tempKf = data.tempKf
        )
    }

    private fun mapWeatherItemEntityToWeatherItem(data: List<WeatherItemEntity>): List<WeatherItem> {

        val weatherItem = ArrayList<WeatherItem>()
        data.map {
            val weather = WeatherItem(
                id = it.id,
                mainWeather = it.mainWeather,
                description = it.description,
                icon = it.icon
            )
            weatherItem.add(weather)
        }
        return weatherItem
    }

    private fun mapCloundEntityToCloud(data: CloudsEntity): Clouds {

        return Clouds(
            all = data.all
        )
    }

    private fun mapWindEntityToWind(data: WindEntity): Wind {

        return Wind(
            speed = data.speed,
            deg = data.deg,
            gust = data.gust
        )
    }

    private fun mapRainEntityToRain(data: RainEntity): Rain {

        return Rain(
            r3h = data.r3h
        )
    }

    private fun mapSysEntityToSys(data: SysEntity): Sys {

        return Sys(
            pod = data.pod
        )
    }

    private fun mapSysEntityToSys2(data: CurrentWeatherSysEntity): CurrentWeatherSys {

        return CurrentWeatherSys(
            type = data.type,
            idSys = data.idSys,
            country = data.country,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

}