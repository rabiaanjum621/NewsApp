package com.example.newsapp.data.api

import com.example.newsapp.data.model.NewsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("aggregate_api/v1/items")
    suspend fun getNewsList(@Query("lineupSlug") value: String): Response<List<NewsItem>>
}