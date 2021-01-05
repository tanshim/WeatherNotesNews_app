package com.example.weanotnew.weather.ui

import android.content.Context
import android.util.Log
import com.example.weanotnew.model.DataStoreRepository
import com.example.weanotnew.util.API_KEY
import com.example.weanotnew.weather.api.WeatherApiFactory
import com.example.weanotnew.weather.model.CityResponse
import com.example.weanotnew.weather.model.Main
import kotlinx.coroutines.flow.first


class WeatherPresenterImpl(view: WeatherContract.WeatherView, context: Context) :
    WeatherContract.WeatherPresenter {

    private var view: WeatherContract.WeatherView? = view

    private var cityResponse: CityResponse? = null
    private var main: Main? = null

    private val repoDataStore = DataStoreRepository(context)

    override suspend fun onHomeCityChange(city: String) {
        repoDataStore.saveHomeCityToDataStore(city)
    }

    override suspend fun onViewCreated() {
        val city = repoDataStore.getHomeCityFromDataStore().first()
        loadWeather(city)
    }

    override fun loadWeather(city: String): Boolean {

        var result = false
        cityResponse = WeatherApiFactory.apiService.getCityCoordinates(
            API_KEY,
            city
        ).execute().body().apply {

            val latitude: String = this?.coord?.lat.toString()
            val longitude: String = this?.coord?.lon.toString()

            main = WeatherApiFactory.apiService.getWeather(latitude, longitude, apiKey = API_KEY)
                .execute().body().apply {
                    view?.setViews(this, city)
                }
        }
        if (main != null) {
            result = true
        }
        return result
    }

    override fun onDestroy() {
        this.view = null
    }
}