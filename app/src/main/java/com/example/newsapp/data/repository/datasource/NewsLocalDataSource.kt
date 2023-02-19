package com.example.newsapp.data.repository.datasource

import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse

interface NewsLocalDataSource {
    suspend fun getNewsFromDB(): CustomResponse<List<NewsItem>?>
    suspend fun saveNewsToDB(news: List<NewsItem>)
    suspend fun clearAll()
}