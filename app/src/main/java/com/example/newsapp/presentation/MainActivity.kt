package com.example.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.R
import com.example.newsapp.data.api.NewsService
import com.example.newsapp.presentation.news.NewsViewModel
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newsViewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        var text = findViewById<TextView>(R.id.tvName)
        newsViewModel.fetchNewsData()
        newsViewModel.newsLiveData?.observe(this, Observer {
            text.text = it.toString()
        })

    }

}