package com.rad.rweather.core.utils.datamapper

import com.rad.rweather.core.data.source.local.entity.*
import com.rad.rweather.core.data.source.local.entity.currentforecast.CurrentWeatherEntity
import com.rad.rweather.core.data.source.local.entity.currentforecast.CurrentWeatherSysEntity
import com.rad.rweather.core.data.source.local.entity.forecast.ForecastEntity
import com.rad.rweather.core.data.source.local.entity.forecast.ListForecastEntity
import com.rad.rweather.core.data.source.local.entity.forecast.MainWeatherEntity
import com.rad.rweather.core.data.source.local.entity.WeatherItemEntity
import com.rad.rweather.core.data.source.remote.response.*
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherResponse
import com.rad.rweather.core.data.source.remote.response.currentforecast.CurrentWeatherSysResponse
import com.rad.rweather.core.data.source.remote.response.forecast.ForecastResponse
import com.rad.rweather.core.data.source.remote.response.forecast.ListForecastResponse
import com.rad.rweather.core.data.source.remote.response.forecast.MainWeatherResponse
import com.rad.rweather.core.data.source.remote.response.forecast.WeatherItemResponse

object MapperResponseToEntity {

    fun mapForecastResponseToEntity(data: ForecastResponse?): ForecastEntity {

        return ForecastEntity(
            city = data?.city?.let { mapCityResponseToEntity(it) },
            cod = data?.cod!!,
            cnt = data.cnt,
            message = data.message,
            list = data.list?.let { mapListForecastResponseToListForecastEntity(it) }

        )
    }

    fun mapCurrentForecastResponseToEntity(data: CurrentWeatherResponse?): CurrentWeatherEntity {

        return CurrentWeatherEntity(
            id = data?.id,
            cod = data?.cod,
            base = data?.base,
            name = data?.name,
            visibility = data?.visibility,
            coord = data?.coord?.let { mapCoordResponseToCoordEntity(it) },
            date = data?.date,
            timezone = data?.timezone,
            main = data?.main?.let { mapMainResponseToMainEntity(it) },
            wind = data?.wind?.let { mapWindResponseToWindEntity(it) },
            rain = data?.rain?.let { mapRainResponseToRainEntity(it) },
            sys = data?.sys?.let { mapSysResponseToSysEntity2(it) },
            clouds = data?.clouds?.let { mapCloudResponseToCloundEntity(it) },
            weather = data?.weather?.let { mapWeatherItemResponseToWeatherItemEntity(it) }
        )
    }

    fun mapCityResponseToEntity(data: CityResponse): CityEntity {

        return CityEntity(
            cityId = data.id,
            name = data.name,
            country = data.country,
            population = data.population,
            timeZone = data.timeZone,
            sunrise = data.sunrise,
            sunset = data.sunset,
            coord = data.coord?.let { mapCoordResponseToCoordEntity(it) }
        )
    }

    fun mapCoordResponseToCoordEntity(data: CoordResponse): CoordEntity {
        return CoordEntity(
            lat = data.lat,
            lon = data.lon
        )
    }

    fun mapListForecastResponseToListForecastEntity(data: List<ListForecastResponse>): List<ListForecastEntity> {

        val forecastList = ArrayList<ListForecastEntity>()
        data.map {
            val weather = ListForecastEntity(
                date = it.date,
                main = it.main?.let { it1 -> mapMainResponseToMainEntity(it1) },
                weather = it.weather?.let { it1 -> mapWeatherItemResponseToWeatherItemEntity(it1) },
                clouds = it.clouds?.let { it1 -> mapCloudResponseToCloundEntity(it1) },
                wind = it.wind?.let { it1 -> mapWindResponseToWindEntity(it1) },
                visibility = it.visibility,
                pop = it.pop,
                rain = it.rain?.let { it1 -> mapRainResponseToRainEntity(it1) },
                sys = it.sys?.let { it1 -> mapSysResponseToSysEntity(it1) },
                dateText = it.dateText
            )
            forecastList.add(weather)
        }
        return forecastList
    }

    fun mapMainResponseToMainEntity(data: MainWeatherResponse): MainWeatherEntity {

        return MainWeatherEntity(
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

    fun mapWeatherItemResponseToWeatherItemEntity(data: List<WeatherItemResponse>): List<WeatherItemEntity> {

        val weatherItemEntity = ArrayList<WeatherItemEntity>()
        data.map {
            val weather = WeatherItemEntity(
                id = it.id,
                mainWeather = it.mainWeather,
                description = it.description,
                icon = it.icon
            )
            weatherItemEntity.add(weather)
        }
        return weatherItemEntity
    }

    fun mapCloudResponseToCloundEntity(data: CloudsResponse): CloudsEntity {

        return CloudsEntity(
            all = data.all
        )
    }

    fun mapWindResponseToWindEntity(data: WindResponse): WindEntity {

        return WindEntity(
            speed = data.speed,
            deg = data.deg,
            gust = data.gust
        )
    }

    fun mapRainResponseToRainEntity(data: RainResponse): RainEntity {

        return RainEntity(
            r3h = data.r3h
        )
    }

    fun mapSysResponseToSysEntity(data: SysResponse): SysEntity {

        return SysEntity(
            pod = data.pod
        )
    }

    fun mapSysResponseToSysEntity2(data: CurrentWeatherSysResponse): CurrentWeatherSysEntity {

        return CurrentWeatherSysEntity(
            type = data.type,
            idSys = data.idSys,
            country = data.country,
            sunrise = data.sunrise,
            sunset = data.sunset
        )
    }

}