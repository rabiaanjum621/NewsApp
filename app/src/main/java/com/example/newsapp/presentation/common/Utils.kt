package com.example.newsapp.presentation.common

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.data.model.NewsItem
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    private const val TAG = "NewsLogs"

    fun dateFormatter(date: Long): String {
        return try {
            return SimpleDateFormat("LLLL dd", Locale.getDefault()).format(date).toString()
        } catch (e: Exception) {
            Log.e(TAG, "Error: ${e.message}")
            ""
        }
    }

    fun snackBar(view: View, message: String, color: Int) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(color)
            .show()
    }

    fun isDeviceOnline(context: Context): Boolean {
        val connManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connManager.getNetworkCapabilities(connManager.activeNetwork)
            return if (networkCapabilities == null) {
                Log.d(TAG, "Device offline")
                false
            } else {
                Log.d(TAG, "Device Online")
                true
            }
        }
        return false
    }

    fun filterNewsByType(newsList: List<NewsItem>, type: String?): List<NewsItem> {
        return (newsList.filter { it.type == type })
    }
}