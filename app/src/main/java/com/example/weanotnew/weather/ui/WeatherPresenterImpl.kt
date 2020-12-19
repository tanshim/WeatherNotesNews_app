package com.example.weanotnew.weather.ui

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.weanotnew.model.DataStoreRepository
import com.example.weanotnew.util.API_KEY
import com.example.weanotnew.weather.api.WeatherApiFactory
import com.example.weanotnew.weather.model.CityResponse
import com.example.weanotnew.weather.model.Main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

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
        Log.d("test07", "onViewCreated: read data store city: ${city}")
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
        Log.d("test07", "loadWeather result: $result")
        return result
    }

    override fun onDestroy() {
        this.view = null
    }
}