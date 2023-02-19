package com.example.newsapp.data.repository.datasource

import com.example.newsapp.data.model.NewsItem
import retrofit2.Response

interface NewsLocalDataSource {
    suspend fun getNewsFromDB(): List<NewsItem>
    suspend fun saveNewsToDB(news: List<NewsItem>)
    suspend fun clearAll()
}