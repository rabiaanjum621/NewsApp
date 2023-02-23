package com.example.newsapp.presentation.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.model.NewsItem
import com.example.newsapp.data.repository.CustomResponse
import com.example.newsapp.domain.usecase.NewsUseCase
import com.example.newsapp.presentation.common.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class NewsViewModel(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    private val _newsLiveData = MutableLiveData<CustomResponse<List<NewsItem>?>>()
    val newsLiveData: LiveData<CustomResponse<List<NewsItem>?>>
        get() = _newsLiveData

    fun fetchNewsData(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _newsLiveData.postValue(CustomResponse.Loading())
            val result = newsUseCase.execute(query)
            _newsLiveData.postValue(result)
        }
    }

    fun filterNewsData(type: String?): List<NewsItem> {
        val newsList: List<NewsItem> = if (_newsLiveData.value?.data != null) {
            _newsLiveData.value?.data!!
        } else {
            mutableListOf()
        }
        return Utils.filterNewsByType(newsList, type)
    }

    override fun onCleared() {
        super.onCleared()
        newsUseCase.onClear()
    }
}