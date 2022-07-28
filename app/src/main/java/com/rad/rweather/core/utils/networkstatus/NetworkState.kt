package com.rad.rweather.core.utils.networkstatus

sealed class NetworkState {
    object Fetched : NetworkState()
    object Error : NetworkState()
}