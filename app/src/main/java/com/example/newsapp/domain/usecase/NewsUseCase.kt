package com.example.newsapp.domain.usecase

import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.domain.repository.NewsRepository
import retrofit2.Response
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {
    suspend fun execute(): Response<List<NewsItem>> = newsRepository.getNewsList()
}