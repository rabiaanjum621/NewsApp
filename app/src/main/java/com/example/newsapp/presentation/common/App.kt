package com.example.newsapp.presentation.common

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var mConnectionStatusCallback: ConnectionStatusCallback

    override fun onCreate() {
        super.onCreate()
        initConnectionStatusCallback()
    }

    private fun initConnectionStatusCallback() {
        try {
            val connectivityManager: ConnectivityManager? =
                getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
            connectivityManager?.registerNetworkCallback(
                NetworkRequest.Builder().build(),
                mConnectionStatusCallback
            )
        } catch (e: Exception) {
            Log.e("Error", "Exception in App class ${e.message}")
        }
    }
}