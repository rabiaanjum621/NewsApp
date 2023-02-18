package com.example.newsapp.domain.repository

import com.example.newsapp.data.model.NewsItem
import retrofit2.Response

interface NewsRepository {
    suspend fun getNewsList() : Response<List<NewsItem>>
}