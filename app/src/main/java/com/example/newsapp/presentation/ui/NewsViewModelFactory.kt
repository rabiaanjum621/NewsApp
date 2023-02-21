package com.example.newsapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.domain.usecase.NewsUseCase
import javax.inject.Inject

class NewsViewModelFactory @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(newsUseCase) as T

    }
}