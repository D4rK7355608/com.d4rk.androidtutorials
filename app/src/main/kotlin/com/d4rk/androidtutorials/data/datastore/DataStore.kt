package com.d4rk.androidtutorials.data.datastore

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.constants.datastore.DataStoreNamesConstants
import com.d4rk.androidtutorials.constants.ui.bottombar.BottomBarRoutes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = DataStoreNamesConstants.DATA_STORE_SETTINGS)

class DataStore(context : Context) {
    private val dataStore = context.dataStore

    companion object {
        @Volatile
        private var instance : DataStore? = null

        fun getInstance(context : Context) : DataStore {
            return instance ?: synchronized(lock = this) {
                instance ?: DataStore(context).also { instance = it }
            }
        }
    }

    // Last used app notifications
    private val lastUsedKey =
            longPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_LAST_USED)
    val lastUsed : Flow<Long> = dataStore.data.map { preferences ->
        preferences[lastUsedKey] ?: 0
    }

    suspend fun saveLastUsed(timestamp : Long) {
        dataStore.edit { preferences ->
            preferences[lastUsedKey] = timestamp
        }
    }

    // Startup
    private val startupKey =
            booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_STARTUP)
    val startup : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[startupKey] != false
    }

    suspend fun saveStartup(isFirstTime : Boolean) {
        dataStore.edit { preferences ->
            preferences[startupKey] = isFirstTime
        }
    }

    // Display
    val themeModeState = mutableStateOf(value = "follow_system")
    private val themeModeKey =
            stringPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_THEME_MODE)
    val themeMode : Flow<String> = dataStore.data.map { preferences ->
        preferences[themeModeKey] ?: "follow_system"
    }

    suspend fun saveThemeMode(mode : String) {
        dataStore.edit { preferences ->
            preferences[themeModeKey] = mode
        }
    }

    private val amoledModeKey =
            booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_AMOLED_MODE)
    val amoledMode : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[amoledModeKey] == true
    }

    suspend fun saveAmoledMode(isChecked : Boolean) {
        dataStore.edit { preferences ->
            preferences[amoledModeKey] = isChecked
        }
    }

    private val dynamicColorsKey =
            booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_DYNAMIC_COLORS)
    val dynamicColors : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[dynamicColorsKey] != false
    }

    suspend fun saveDynamicColors(isChecked : Boolean) {
        dataStore.edit { preferences ->
            preferences[dynamicColorsKey] = isChecked
        }
    }

    private val bouncyButtonsKey =
            booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_BOUNCY_BUTTONS)
    val bouncyButtons : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[bouncyButtonsKey] != false
    }

    suspend fun saveBouncyButtons(isChecked : Boolean) {
        dataStore.edit { preferences ->
            preferences[bouncyButtonsKey] = isChecked
        }
    }

    fun getStartupPage() : Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_STARTUP_PAGE)]
                ?: BottomBarRoutes.HOME
        }
    }

    suspend fun saveStartupPage(startupPage : String) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_STARTUP_PAGE)] =
                    startupPage
        }
    }

    fun getShowBottomBarLabels() : Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_SHOW_BOTTOM_BAR_LABELS)] != false
        }
    }

    private val languageKey =
            stringPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_LANGUAGE)

    fun getLanguage() : Flow<String> = dataStore.data.map { preferences ->
        preferences[languageKey] ?: "en"
    }

    suspend fun saveLanguage(language : String) {
        dataStore.edit { preferences ->
            preferences[languageKey] = language
        }
    }

    // Usage and Diagnostics
    private val usageAndDiagnosticsKey =
            booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_USAGE_AND_DIAGNOSTICS)
    val usageAndDiagnostics : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[usageAndDiagnosticsKey] ?: ! BuildConfig.DEBUG
    }

    suspend fun saveUsageAndDiagnostics(isChecked : Boolean) {
        dataStore.edit { preferences ->
            preferences[usageAndDiagnosticsKey] = isChecked
        }
    }

    // Ads
    private val adsKey = booleanPreferencesKey(name = DataStoreNamesConstants.DATA_STORE_ADS)
    val ads : Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[adsKey] ?: ! BuildConfig.DEBUG
    }

    suspend fun saveAds(isChecked : Boolean) {
        dataStore.edit { preferences ->
            preferences[adsKey] = isChecked
        }
    }
}