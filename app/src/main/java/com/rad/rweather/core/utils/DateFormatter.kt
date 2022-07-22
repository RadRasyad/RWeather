package com.rad.rweather.core.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateFormatter {

    fun getDay(date: String): String? {

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(date, pattern)

        return localDateTime.format(DateTimeFormatter.ofPattern("EEEE"))
    }

    fun getHour(date: String): String? {

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(date, pattern)

        return localDateTime.format(DateTimeFormatter.ofPattern("HH.mm"))
    }

    fun getDayNHour(date: String): String? {

        val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val localDateTime = LocalDateTime.parse(date, pattern)

        return localDateTime.format(DateTimeFormatter.ofPattern("EEEE, HH.mm"))
    }
}