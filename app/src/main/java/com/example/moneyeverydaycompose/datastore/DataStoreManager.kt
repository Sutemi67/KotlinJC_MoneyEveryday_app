package com.example.moneyeverydaycompose.datastore

import android.content.Context
import android.icu.util.Calendar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.moneyeverydaycompose.InputDataStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(
    private val context: Context
) {

    private companion object {
        val RESULT_TEXT_KEY = intPreferencesKey("result")
        val DATE_OF_CLEAR_KEY = longPreferencesKey("dateOfClear")
        val LISTS_KEY = stringPreferencesKey("lists")
    }

    suspend fun saveSummaryText(resultTextSettings: ResultTextSettings) {
        saveToDataStore(RESULT_TEXT_KEY, resultTextSettings.resultText)
    }

    fun getSummaryText(): Flow<ResultTextSettings> = getFromDataStore(RESULT_TEXT_KEY) {
        ResultTextSettings(it ?: 0)
    }

    suspend fun saveClearDate(dateTextSettings: DateTextSettings) {
        saveToDataStore(DATE_OF_CLEAR_KEY, dateTextSettings.dateOfClear)
    }

    fun getClearData(): Flow<DateTextSettings> = getFromDataStore(DATE_OF_CLEAR_KEY) {
        DateTextSettings(it ?: Calendar.getInstance().timeInMillis)
    }

    suspend fun saveToDataStore(inputDataStorage: InputDataStorage) {
        saveToDataStore(LISTS_KEY, inputDataStorage.operations.joinToString(","))
    }

    fun getLists(): Flow<InputDataStorage> = getFromDataStore(LISTS_KEY) {
        InputDataStorage(it?.split(",")?.toMutableList() ?: mutableListOf())
    }

    private suspend fun <T> saveToDataStore(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { pref ->
            pref[key] = value
        }
    }

    private fun <T, R> getFromDataStore(key: Preferences.Key<T>, transform: (T?) -> R): Flow<R> {
        return context.dataStore.data.map { pref ->
            transform(pref[key])
        }
    }
}
