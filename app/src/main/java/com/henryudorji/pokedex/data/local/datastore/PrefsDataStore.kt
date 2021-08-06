
package com.henryudorji.pokedex.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences


class PrefsDataStore(context: Context) {

    private val Context.dataStore by preferencesDataStore("app_preferences")
    private val dataStore = context.dataStore

    suspend fun saveUIMode(isNightMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[UI_MODE_KEY] = isNightMode
        }
    }

    val uiMode: Flow<Boolean> = dataStore.data
        .map { preferences ->
            val uiMode = preferences[UI_MODE_KEY] ?: false
            uiMode
        }

    companion object {
        private val UI_MODE_KEY = booleanPreferencesKey("ui_mode")
    }
}

