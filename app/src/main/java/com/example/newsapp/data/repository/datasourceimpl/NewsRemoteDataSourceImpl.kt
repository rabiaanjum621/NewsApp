package com.example.newsapp.data.repository.datasourceimpl

import com.example.newsapp.data.api.NewsService
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsService: NewsService
): NewsRemoteDataSource {
    override suspend fun getNewsList(): Response<List<NewsItem>> {
        return newsService.getNewsList()
    }

}