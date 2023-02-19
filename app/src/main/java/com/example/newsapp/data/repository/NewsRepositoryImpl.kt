package com.example.newsapp.data.repository

import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsList(): List<NewsItem> {
      return getNewsDataFromDB()
    }

    override fun onClear() {
        newsRemoteDataSource.onClear()
    }

    private suspend fun getNewsDataFromAPI() : List<NewsItem>?{
            return newsRemoteDataSource.getNewsList().body()
    }

    // check internet if on then API otherwise DB
    private suspend fun getNewsDataFromDB() : List<NewsItem> {
        var news = getNewsDataFromAPI()
        if(news != null) {
            newsLocalDataSource.clearAll()
            newsLocalDataSource.saveNewsToDB(news)
        } else {
            news = mutableListOf()
        }
        return news

    }
}