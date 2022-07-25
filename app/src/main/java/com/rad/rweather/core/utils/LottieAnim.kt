package com.rad.rweather.core.utils

fun getLottieSrc(id: String): String {

    return when(id) {
        "01d" -> "weather_sunny.json"
        "01n" -> "weather_night.json"
        "02d" -> "weather_cloudy_day.json"
        "02n" -> "weather_cloudy_night.json"
        "03d" -> "weather_cloudy.json"
        "03n" -> "weather_cloudy.json"
        "04d" -> "weather_cloudy.json"
        "04n" -> "weather_cloudy.json"
        "09d" -> "weather_hard_rain.json"
        "09n" -> "weather_hard_rain.json"
        "10d" -> "weather_rainy_day.json"
        "10n" -> "weather_rainy_night.json"
        "11d" -> "weather_hard_rain.json"
        "11n" -> "weather_hard_rain.json"
        "13d" -> "weather_snow_day.json"
        "13n" -> "weather_snow_night.json"
        "50d" -> "weather_mist.json"
        "50n" -> "weather_mist.json"
        else ->{
            "weather_sunny.json"
        }
    }
}