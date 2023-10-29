package com.example.jetpacknewsapp.network

import com.example.jetpacknewsapp.model.NewsMainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("ApiKey") apiKey: String,
        @Query("country") country: String
    ): Response<NewsMainResponse>
}