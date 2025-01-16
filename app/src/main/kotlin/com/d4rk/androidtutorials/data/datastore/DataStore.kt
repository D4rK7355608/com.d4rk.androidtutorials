package com.d4rk.androidtutorials.data.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.d4rk.android.libs.apptoolkit.data.datastore.CommonDataStore
import com.d4rk.androidtutorials.utils.constants.datastore.AppDataStoreConstants
import com.d4rk.androidtutorials.utils.constants.ui.bottombar.BottomBarRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(context : Context) : CommonDataStore(context) {

    companion object {
        @Volatile
        private var instance : DataStore? = null

        fun getInstance(context : Context) : DataStore {
            return instance ?: synchronized(this) {
                instance ?: DataStore(context.applicationContext).also { instance = it }
            }
        }
    }

    fun getStartupPage() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(name = AppDataStoreConstants.DATA_STORE_STARTUP_PAGE)]
                ?: BottomBarRoutes.HOME
        }
    }

    suspend fun saveStartupPage(startupPage : String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(name = AppDataStoreConstants.DATA_STORE_STARTUP_PAGE)] =
                    startupPage
        }
    }

    fun getShowBottomBarLabels() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(name = AppDataStoreConstants.DATA_STORE_SHOW_BOTTOM_BAR_LABELS)] != false
        }
    }
}