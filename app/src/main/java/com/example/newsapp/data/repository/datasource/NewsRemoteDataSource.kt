package com.example.newsapp.data.repository.datasource

import com.example.newsapp.data.model.NewsItem
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getNewsList() : Response<List<NewsItem>>
}