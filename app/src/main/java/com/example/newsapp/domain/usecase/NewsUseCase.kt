package com.example.newsapp.domain.usecase

import android.content.Context
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    val context: Context,
    private val newsRepository: NewsRepository
) {
    suspend fun execute(query: String): CustomResponse<List<NewsItem>?> = newsRepository.getNewsList(context,query)

    fun onClear() = newsRepository.onClear()
}