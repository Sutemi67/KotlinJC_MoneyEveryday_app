package com.example.moneyeverydaycompose.datastore

import android.content.Context
import android.icu.util.Calendar
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.moneyeverydaycompose.InputDataStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
   "data_store"
)

class DataStoreManager(private val context: Context) {
   
   suspend fun saveSummaryText(resultTextSettings: ResultTextSettings) {
      context.dataStore.edit { pref ->
         pref[intPreferencesKey("result")] = resultTextSettings.resultText
      }
   }
   
   fun getSummaryText() = context.dataStore.data.map { pref ->
      return@map ResultTextSettings(
         pref[intPreferencesKey("result")] ?: 0
      )
   }
   
   
   suspend fun saveClearDate(dateTextSettings: DateTextSettings) {
      context.dataStore.edit { pref ->
         pref[longPreferencesKey("dateOfClear")] = dateTextSettings.dateOfClear
      }
   }
   
   fun getClearData() = context.dataStore.data.map { pref ->
      return@map DateTextSettings(
         pref[longPreferencesKey("dateOfClear")] ?: Calendar.getInstance().timeInMillis
      )
   }
   
   suspend fun saveToDataStore(inputDataStorage: InputDataStorage) {
      context.dataStore.edit { pref ->
         pref[stringPreferencesKey("lists")] = inputDataStorage.operations.joinToString(",")
      }
   }
   
   //    val getLists: Flow<InputDataStorage> = context.dataStore.data.map { pref ->
//        return@map InputDataStorage(
//            pref[stringPreferencesKey("lists")]?.split(",")?.toMutableList() ?: mutableListOf()
//        )
//    }
   fun getLists(): Flow<InputDataStorage> = context.dataStore.data.map { pref ->
      return@map InputDataStorage(
         pref[stringPreferencesKey("lists")]?.split(",")?.toMutableList() ?: mutableListOf()
      )
   }
}
