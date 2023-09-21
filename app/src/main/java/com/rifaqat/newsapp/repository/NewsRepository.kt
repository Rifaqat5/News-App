package com.rifaqat.newsapp.repository

import com.rifaqat.newsapp.apis.RetrofitInstance
import com.rifaqat.newsapp.db.ArticleDatabase
import com.rifaqat.newsapp.models.Article

class NewsRepository(val db:ArticleDatabase) {
    suspend fun getBreakingNews(countryCode: String,pageNumber: Int)=
        RetrofitInstance.api.getBreakingNews(countryCode,pageNumber)

    suspend fun searchNews(searchQuery: String,pageNumber: Int)=
        RetrofitInstance.api.searchForNews(searchQuery,pageNumber)

    suspend fun upsert(article:Article)=db.getArticleDao().upsert(article)

    fun getSavedNews()=db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article)=db.getArticleDao().deleteArticles(article)
}