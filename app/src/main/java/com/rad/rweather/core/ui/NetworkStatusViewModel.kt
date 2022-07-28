package com.rad.rweather.core.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rad.rweather.core.utils.networkstatus.NetworkState
import com.rad.rweather.core.utils.networkstatus.NetworkStatusTracker
import com.rad.rweather.core.utils.networkstatus.map
import kotlinx.coroutines.Dispatchers

class NetworkStatusViewModel(
    networkStatusTracker: NetworkStatusTracker,
) : ViewModel() {

    val state =
        networkStatusTracker.networkStatus
            .map(
                onUnavailable = { NetworkState.Error },
                onAvailable = { NetworkState.Fetched },
            )
            .asLiveData(Dispatchers.IO)
}