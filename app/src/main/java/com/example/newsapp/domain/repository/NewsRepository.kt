package com.example.newsapp.domain.repository

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsList(context: Context) : List<NewsItem>
    fun onClear()
}