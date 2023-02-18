package com.example.newsapp.presentation

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun dateFormatter(date: Long): String{
        return try {
            return SimpleDateFormat( "LLLL dd", Locale.getDefault()).format(date).toString()
        } catch (e: Exception){
            Log.e("Error", "Error: ${e.message}")
            ""
        }
    }
}