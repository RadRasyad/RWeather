package com.rad.rweather.core.utils.datamapper

import com.rad.rweather.core.data.source.local.entity.*
import com.rad.rweather.core.data.source.remote.response.*
import com.rad.rweather.core.domain.model.*

object MapperEntityToDomain {

    fun mapEntityToDomain(data: ForecastEntity): Forecast {

        return Forecast(

            city = data.city?.let { mapCityEntityToCity(it) },
            cod = data.cod,
            cnt = data.cnt,
            message = data.message,
            list = data.list?.let { mapListForecastEntityToListForecast(it) }

        )
    }

    fun mapCityEntityToCity(data: CityEntity): City {

        return City(

            id = data.cityId,
            name = data.name,
            country = data.country,
            population = data.population,
            timeZone = data.timeZone,
            sunrise = data.sunrise,
            sunset = data.sunset,
            coord = data.coord?.let { mapCoordEntityToCoord(it) }

        )
    }

    fun mapCoordEntityToCoord(data: CoordEntity): Coord {

        return Coord(
            lat = data.lat,
            lon = data.lon
        )
    }

    fun mapListForecastEntityToListForecast(data: List<ListForecastEntity>): List<ListForecast> {

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

    fun mapMainEntityToMain(data: MainWeatherEntity): MainWeather {

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

    fun mapWeatherItemEntityToWeatherItem(data: List<WeatherItemEntity>): List<WeatherItem> {

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

    fun mapCloundEntityToCloud(data: CloudsEntity): Clouds {

        return Clouds(
            all = data.all
        )
    }

    fun mapWindEntityToWind(data: WindEntity): Wind {
        return Wind(
            speed = data.speed,
            deg = data.deg,
            gust = data.gust
        )
    }

    fun mapRainEntityToRain(data: RainEntity): Rain {
        return Rain(
            r3h = data.r3h
        )
    }

    fun mapSysEntityToSys(data: SysEntity): Sys {
        return Sys(
            pod = data.pod
        )
    }

}