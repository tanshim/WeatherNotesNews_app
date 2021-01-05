package com.example.weanotnew.news.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NewsApiFactory {

    private const val NEWS_BASE_URL = "https://newsapi.org/v2/"

    private val retrofit2 = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(NEWS_BASE_URL)
        .build()

    val newsApiService = retrofit2.create(NewsApiService::class.java)
}