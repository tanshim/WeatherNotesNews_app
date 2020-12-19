package com.example.weanotnew.weather.ui

import android.content.Context
import com.example.weanotnew.BasePresenter
import com.example.weanotnew.BaseView
import com.example.weanotnew.weather.model.Main

interface WeatherContract {

    interface WeatherPresenter : BasePresenter {
        suspend fun onViewCreated()
        fun loadWeather(city: String): Boolean
        suspend fun onHomeCityChange(city: String)
    }

    interface WeatherView : BaseView<WeatherPresenter> {
        fun setViews(main: Main?, city: String)
        fun searchCity(city: String)
    }
}