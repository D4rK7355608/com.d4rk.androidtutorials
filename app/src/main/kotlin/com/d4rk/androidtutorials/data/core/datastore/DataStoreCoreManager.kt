package com.d4rk.androidtutorials.data.core.datastore

import android.content.Context
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.utils.constants.ui.bottombar.BottomBarRoutes
import com.d4rk.androidtutorials.data.datastore.DataStore
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull

open class DataStoreCoreManager(protected val context : Context) {

    var isDataStoreLoaded : Boolean = false
    var dataStore : DataStore = AppCoreManager.dataStore

    suspend fun initializeDataStore() : Boolean = coroutineScope {

        listOf(async {
            dataStore.getStartupPage().firstOrNull() ?: BottomBarRoutes.HOME
        } , async {
            dataStore.getShowBottomBarLabels().firstOrNull() ?: true
        } , async {
            dataStore.getLanguage().firstOrNull() ?: "en"
        } , async {
            dataStore.lastUsed.firstOrNull() ?: 0L
        } , async {
            dataStore.startup.firstOrNull() ?: true
        } , async {
            dataStore.themeMode.firstOrNull() ?: "follow_system"
        } , async {
            dataStore.amoledMode.firstOrNull() ?: false
        } , async {
            dataStore.dynamicColors.firstOrNull() ?: false
        } , async {
            dataStore.bouncyButtons.firstOrNull() ?: false
        } , async {
            dataStore.ads.firstOrNull() ?: (BuildConfig.DEBUG)
        } , async {
            dataStore.usageAndDiagnostics.firstOrNull() ?: ! BuildConfig.DEBUG
        }).awaitAll()

        isDataStoreLoaded = true
        return@coroutineScope this@DataStoreCoreManager.isDataStoreLoaded
    }
}