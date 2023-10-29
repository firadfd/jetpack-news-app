package com.example.jetpacknewsapp.model

data class NewsStateHolder(
    val loading: Boolean = false,
    val data: NewsMainResponse? = null,
    val error: String = ""
)
