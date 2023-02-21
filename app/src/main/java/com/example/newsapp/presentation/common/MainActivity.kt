package com.example.newsapp.presentation.common

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mConnectionStatusCallback: ConnectionStatusCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        checkInternetConnection()
    }

    private fun checkInternetConnection() {
        mConnectionStatusCallback.isConnectionLostLiveData.observe(this) { connection ->
            if (!connection) {
                Utils.snackBar(
                    findViewById(android.R.id.content),
                    "No Internet Connection",
                    Color.RED
                )
            }
        }
    }
}