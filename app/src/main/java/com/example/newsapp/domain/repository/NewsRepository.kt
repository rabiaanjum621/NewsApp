package com.example.newsapp.domain.repository

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse

interface NewsRepository {
    suspend fun getNewsList(context: Context, query: String): CustomResponse<List<NewsItem>?>
    fun onClear()
}