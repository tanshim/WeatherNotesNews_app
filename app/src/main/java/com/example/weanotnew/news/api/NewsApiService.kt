package com.example.weanotnew.news.api

import com.example.weanotnew.news.model.ArticlesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    fun getTrendingNews(
        @Query(QUERY_PARAM_SOURCES) sources: String = "bbc-news,time,wired,al-jazeera-english",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_PAGE_SIZE) pageSize: Int = 20
    ): Call<ArticlesResponse>


    companion object {
        private const val QUERY_PARAM_API_KEY = "apiKey"
        private const val QUERY_PARAM_SOURCES = "sources"
        private const val QUERY_PARAM_PAGE_SIZE = "pageSize"
    }
}