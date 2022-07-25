package com.rad.rweather.core.utils

import com.rad.rweather.core.domain.model.ListForecast

object ForecastSort {

    fun sortDaily(listForecast: List<ListForecast>): List<ListForecast> {

        val days = arrayListOf<String>()
        val mappedForecast = arrayListOf<ListForecast>()

        listForecast.forEachIndexed { _, list ->
            if (days.contains(list.dateText?.substringBefore(" ")).not()) {
                list.dateText?.substringBefore(" ").let {
                    if (it != null) {
                        days.add(it)
                    }
                }
            }
        }

        days.forEach { day ->
            val fArray = listForecast.filter { it.dateText?.substringBefore(" ").equals(day) }
            fArray.forEach {
                mappedForecast.add(it)
            }
        }

        return mappedForecast
            .sortedBy { it.dateText }
            .distinctBy { it.dateText?.substringBefore(" ") }.toList()
    }

    fun sortHourly(listForecast: List<ListForecast>): List<ListForecast> {

        return listForecast.sortedBy { it.dateText }
            .subList(0,8)

    }


}