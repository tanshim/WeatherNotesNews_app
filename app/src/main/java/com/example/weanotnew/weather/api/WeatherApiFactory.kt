package com.example.weanotnew.weather.api

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiFactory {

    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    const val BASE_IMAGE_URL = ""

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(WeatherApiService::class.java)
}