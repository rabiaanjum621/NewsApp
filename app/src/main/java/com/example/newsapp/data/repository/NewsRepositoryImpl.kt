package com.example.newsapp.data.repository

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.datasource.NewsLocalDataSource
import com.example.newsapp.data.repository.datasource.NewsRemoteDataSource
import com.example.newsapp.domain.repository.NewsRepository
import com.example.newsapp.presentation.common.Utils
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsLocalDataSource: NewsLocalDataSource,
    private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsList(context: Context): CustomResponse<List<NewsItem>?> {
      return getNewsDataFromDB(context)
    }

    // TODO improvements user go offline while APi is running, not getting callback from API
    private suspend fun getNewsDataFromDB(context: Context) : CustomResponse<List<NewsItem>?> {
          val news: CustomResponse<List<NewsItem>?> = if(Utils.isDeviceOnline(context)) {
                getNewsDataFromAPI()
            } else {
                newsLocalDataSource.getNewsFromDB()
            }
            if (news.data != null) {
                newsLocalDataSource.clearAll()
                newsLocalDataSource.saveNewsToDB(news.data)
            }

        return news

    }

    private suspend fun getNewsDataFromAPI() : CustomResponse<List<NewsItem>?>{

        val newsFromAPI: CustomResponse<List<NewsItem>?> = try {
            val response = newsRemoteDataSource.getNewsList()
            if (response.isSuccessful) {
                CustomResponse.Success(response.body())
            } else {
                CustomResponse.Error(
                    errorMessage = "Error fetching data from API  message: ${response.errorBody()}",
                    errorCode = response.code()
                )
            }
        } catch (e: Exception){
            CustomResponse.Error("Exception fetching data from API ${e.message}")

        }
        return newsFromAPI
    }

    override fun onClear() {
        newsRemoteDataSource.onClear()
    }

}