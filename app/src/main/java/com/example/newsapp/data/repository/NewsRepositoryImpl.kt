package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsList(): Response<List<NewsItem>> {
      return getNewsDataFromAPI()
    }

    private suspend fun getNewsDataFromAPI() : Response<List<NewsItem>>{
            return newsRemoteDataSource.getNewsList()

    }
}