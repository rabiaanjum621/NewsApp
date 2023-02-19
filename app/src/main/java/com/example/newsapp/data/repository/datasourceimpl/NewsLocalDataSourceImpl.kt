package com.example.newsapp.data.repository.datasourceimpl

import com.example.newsapp.data.db.NewsDao
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsLocalDataSourceImpl @Inject constructor(
    private val newsDao: NewsDao
) : NewsLocalDataSource {
    override suspend fun getNewsFromDB(): List<NewsItem> {
        return  newsDao.getNewsData()
    }

    override suspend fun saveNewsToDB(news: List<NewsItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.saveNewsData(news)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            newsDao.deleteAllNewsData()
        }
    }
}