package com.example.newsapp.presentation.news_home_fragment

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


class NewsViewModel : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsItem>>()
    val newsLiveData: LiveData<List<NewsItem>>
        get() = _newsLiveData

    private fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.cbc.ca/aggregate_api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var newsService: NewsService = provideRetrofitInstance().create(NewsService::class.java)

    fun fetchNewsData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = newsService.getNewsList()
                _newsLiveData.postValue(result.body())
            } catch (e: Exception) {
                Log.d("Error", "result ${e.message}")
            }
        }
    }

    fun filterNewsData(type: String?): List<NewsItem> {
        return (_newsLiveData.value?.filter { it.type == type }) ?: mutableListOf()
    }
}