package com.example.weanotnew.weather.api

import com.example.weanotnew.weather.model.CityResponse
import com.example.weanotnew.weather.model.Main
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather?")
    fun getCityCoordinates(
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_CITY_NAME) city: String = ""
    ): Call<CityResponse>

    @GET("onecall?")
    fun getWeather(
        @Query(QUERY_PARAM_CITY_LATITUDE) lat: String = "",
        @Query(QUERY_PARAM_CITY_LONGITUDE) lon: String = "",
        @Query(QUERY_PARAM_EXCLUDE) exclude: String = "minutely,alerts",
        @Query(QUERY_PARAM_API_KEY) apiKey: String = "",
        @Query(QUERY_PARAM_UNITS) units: String = "metric",
        @Query(QUERY_PARAM_LANGUAGE) lang: String = "ru"
    ): Call<Main>


    companion object {
        private const val QUERY_PARAM_API_KEY = "appid"
        private const val QUERY_PARAM_CITY_NAME = "q"
        private const val QUERY_PARAM_CITY_LATITUDE = "lat"
        private const val QUERY_PARAM_CITY_LONGITUDE = "lon"
        private const val QUERY_PARAM_EXCLUDE = "exclude"
        private const val QUERY_PARAM_UNITS = "units"
        private const val QUERY_PARAM_LANGUAGE = "lang"
    }
}