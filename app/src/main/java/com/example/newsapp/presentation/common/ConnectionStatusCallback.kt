package com.example.newsapp.presentation.common

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.MutableLiveData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConnectionStatusCallback @Inject constructor() : ConnectivityManager.NetworkCallback() {

    private val activeNetworks: MutableList<Network> = mutableListOf()

    var isConnectionLostLiveData = MutableLiveData<Boolean>()

    override fun onLost(network: Network) {
        super.onLost(network)
        activeNetworks.removeAll { activeNetwork -> activeNetwork == network }
        if (activeNetworks.isEmpty())
            isConnectionLostLiveData.postValue(false)
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        if (activeNetworks.none { activeNetwork -> activeNetwork == network }) {
            activeNetworks.add(network)
        }
        if (activeNetworks.size == 1)
            isConnectionLostLiveData.postValue(true)
    }
}