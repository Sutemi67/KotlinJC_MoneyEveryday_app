package com.example.moneyeverydaycompose.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
    "data_store"
)

class DataStoreManager(private val context: Context) {
    suspend fun saveSettings(settingsData: SettingsData) {
        context.dataStore.edit { pref ->
            pref[intPreferencesKey("result")]=settingsData.resultText

        }
    }
    fun getSettings()=context.dataStore.data.map { pref ->
        return@map SettingsData(
        pref[intPreferencesKey("result")] ?:0
        )
    }
}