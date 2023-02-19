package com.example.newsapp.domain.repository

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsList(context: Context) : CustomResponse<List<NewsItem>?>
    fun onClear()
}