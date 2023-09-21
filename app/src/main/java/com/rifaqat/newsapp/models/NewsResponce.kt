package com.rifaqat.newsapp.models

data class NewsResponce(
    val articles: MutableList<Article>,
    val status: String,
    val totalResults: Int
)