package com.example.newsapp.presentation.common

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
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
        checkInternetConnection()
    }

    private fun checkInternetConnection(){
        mConnectionStatusCallback.isConnectionLostLiveData.observe(this, Observer { connection ->
            if(!connection){
                Utils.snackBar(findViewById(android.R.id.content), "No Internet Connection",Color.RED)
            } else {
                Utils.snackBar(findViewById(android.R.id.content), "Connected",ContextCompat.getColor(this,R.color.green))
            }
        })
    }
}