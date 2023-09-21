package com.rifaqat.newsapp.apis

import com.rifaqat.newsapp.models.NewsResponce
import com.rifaqat.newsapp.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Locale.IsoCountryCode

interface NewsApi {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String="us",
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String=API_KEY
    ): Response<NewsResponce>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int=1,
        @Query("apiKey")
        apiKey: String=API_KEY
    ): Response<NewsResponce>
}