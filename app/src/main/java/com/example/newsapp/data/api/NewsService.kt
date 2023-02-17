package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsItem
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {

    @GET("items")
    suspend fun getNewsList(): Response<List<NewsItem>>
}