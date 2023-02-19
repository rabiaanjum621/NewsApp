package com.example.newsapp.data.repository

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.presentation.common.Utils
import kotlinx.coroutines.delay
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsList(context: Context): List<NewsItem> {
      return getNewsDataFromDB(context)
    }

    override fun onClear() {
        newsRemoteDataSource.onClear()
    }

    private suspend fun getNewsDataFromAPI() : List<NewsItem>?{
            return newsRemoteDataSource.getNewsList().body()
    }

    // TODO improvements user go offline while APi is running, not getting callback from API
    private suspend fun getNewsDataFromDB(context: Context) : List<NewsItem> {
        var news: List<NewsItem>?
        if(Utils.isDeviceOnline(context)) {
            news = getNewsDataFromAPI()
            if (news != null) {
                newsLocalDataSource.clearAll()
                newsLocalDataSource.saveNewsToDB(news)
            } else {
                news = mutableListOf()
            }
        } else {
                news = newsLocalDataSource.getNewsFromDB()
        }
        return news

    }
}