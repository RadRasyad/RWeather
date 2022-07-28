package com.rad.rweather.core.utils.networkstatus

sealed class NetworkStatus {
    object Available : NetworkStatus()
    object Unavailable : NetworkStatus()
}