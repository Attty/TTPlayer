package com.example.ttplayer.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreSettings(
    private val context: Context
) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("DarkTheme")
        val DARK_THEME_KEY = booleanPreferencesKey("dark_theme")
    }

    val getTheme: Flow<Boolean?> = context.dataStore.data
        .map { prefs ->
            prefs[DARK_THEME_KEY] ?: false

        }

    suspend fun saveTheme(darkTheme: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[DARK_THEME_KEY] = darkTheme
        }
    }
}