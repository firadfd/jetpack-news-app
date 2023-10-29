package com.example.jetpacknewsapp.di

import com.example.jetpacknewsapp.network.ApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    const val apiKey = "547f8223570f434db2f9fe5753d5d1d6"

    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/"

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): ApiServices = Retrofit.Builder()
        .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
        .build().create(ApiServices::class.java)
}