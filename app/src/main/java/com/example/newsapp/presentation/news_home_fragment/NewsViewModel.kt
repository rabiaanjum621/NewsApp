package com.example.newsapp.presentation.news_home_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<List<NewsItem>>()
    val newsLiveData: LiveData<List<NewsItem>>
        get() = _newsLiveData

    fun fetchNewsData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = newsUseCase.execute()
                _newsLiveData.postValue(result)
            } catch (e: Exception) {
                Log.d("Error", "result ${e.message}")
            }
        }
    }

    fun filterNewsData(type: String?): List<NewsItem> {
        return (_newsLiveData.value?.filter { it.type == type }) ?: mutableListOf()
    }

    override fun onCleared() {
        super.onCleared()
        newsUseCase.onClear()
    }
}