package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): List<NewsItem> = newsRepository.getNewsList()

    fun onClear() = newsRepository.onClear()
}