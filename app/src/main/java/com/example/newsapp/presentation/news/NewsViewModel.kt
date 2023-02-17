package com.example.newsapp.presentation.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.api.NewsService
import com.example.newsapp.data.model.NewsItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class NewsViewModel: ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsItem>>()
    var newsLiveData: LiveData<List<NewsItem>>? = null
    get() = _newsLiveData

    fun provideRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbc.ca/aggregate_api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    var newsService = provideRetrofitInstance().create(NewsService::class.java)

     fun fetchNewsData(){
         CoroutineScope(Dispatchers.IO).launch {
             try {
                 val result = newsService.getNewsList()
             _newsLiveData.postValue(result.body())
             } catch (e: Exception) {
                 Log.d("Rabia", "result ${e.message}")
             }
         }
     }
}