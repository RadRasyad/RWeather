package com.rad.rweather.core.utils

fun getLottieSrc(id: String): String {

    return when(id) {
        "01d" -> "@raw/weather_sunny"
        "01n" -> "@raw/weather_night"
        "02d" -> "@raw/weather_cloudy_day"
        "02n" -> "@raw/weather_cloudy_night"
        "03d" -> "@raw/weather_cloudy"
        "03n" -> "@raw/weather_cloudy"
        "04d" -> "@raw/weather_cloudy"
        "04n" -> "@raw/weather_cloudy"
        "09d" -> "@raw/weather_hard_rain"
        "09n" -> "@raw/weather_hard_rain"
        "10d" -> "@raw/weather_rainy_day"
        "10n" -> "@raw/weather_rainy_night"
        "11d" -> "@raw/weather_hard_rain"
        "11n" -> "@raw/weather_hard_rain"
        "13d" -> "@raw/weather_snow_day"
        "13n" -> "@raw/weather_snow_night"
        "50d" -> "@raw/weather_mist"
        "50n" -> "@raw/weather_mist"
        else ->{
            "@raw/weather_sunny"
        }
    }
}