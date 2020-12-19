package com.example.weanotnew.model

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.*
import java.io.IOException


const val PREFERENCE_NAME = "my_preference"

class DataStoreRepository(context: Context) {

    private object PreferenceKeys {
        val homeCity = preferencesKey<String>("home_city")
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCE_NAME
    )

    suspend fun saveHomeCityToDataStore(city: String) {
        dataStore.edit { preference ->
            preference[PreferenceKeys.homeCity] = city
        }
    }

    suspend fun getHomeCityFromDataStore(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.d("DataStore", exception.message.toString())
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                val homeCity = preference[PreferenceKeys.homeCity] ?: "Almaty"
                homeCity
            }
    }

}