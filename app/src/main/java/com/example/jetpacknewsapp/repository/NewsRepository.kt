package com.example.jetpacknewsapp.repository

import com.example.jetpacknewsapp.network.ApiServices
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiServices: ApiServices) {
    suspend fun getNews(apiKey: String, country: String) = apiServices.getNews(apiKey, country)
}