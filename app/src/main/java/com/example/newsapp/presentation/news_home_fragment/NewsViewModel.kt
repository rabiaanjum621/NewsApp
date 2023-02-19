package com.example.newsapp.presentation.news_home_fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import com.example.newsapp.domain.usecase.NewsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<CustomResponse<List<NewsItem>?>>()
    val newsLiveData: LiveData<CustomResponse<List<NewsItem>?>>
        get() = _newsLiveData

    fun fetchNewsData() {
        _newsLiveData.postValue(CustomResponse.Loading())
        viewModelScope.launch(Dispatchers.IO) {
                val result = newsUseCase.execute()
                _newsLiveData.postValue(result)
            }
        }

    fun filterNewsData(type: String?): List<NewsItem> {
        return (_newsLiveData.value?.data?.filter { it.type == type }) ?: mutableListOf()
    }

    override fun onCleared() {
        super.onCleared()
        newsUseCase.onClear()
    }
}