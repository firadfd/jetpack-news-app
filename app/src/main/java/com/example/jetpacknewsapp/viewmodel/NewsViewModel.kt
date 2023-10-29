package com.example.jetpacknewsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpacknewsapp.di.HiltModules
import com.example.jetpacknewsapp.model.ApiResponseState
import com.example.jetpacknewsapp.model.NewsMainResponse
import com.example.jetpacknewsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repository: NewsRepository) : ViewModel() {

    private val _news =
        MutableStateFlow<ApiResponseState<NewsMainResponse>>(ApiResponseState.Loading)

    val news: StateFlow<ApiResponseState<NewsMainResponse>> = _news


    init {
        viewModelScope.launch(Dispatchers.IO) {
            _news.emit((ApiResponseState.Loading))
            val result = repository.getNews(apiKey = HiltModules.apiKey, "us")
            if (result.isSuccessful) {
                _news.emit(ApiResponseState.Success(result.body()!!))
            } else {
                _news.emit(ApiResponseState.Error(result.errorBody().toString()))
            }
        }
    }
}