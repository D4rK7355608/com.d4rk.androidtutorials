@file:Suppress("DEPRECATION")

package com.d4rk.androidtutorials.data.core

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.multidex.MultiDexApplication
import androidx.room.Room
import com.d4rk.androidtutorials.data.core.ads.AdsCoreManager
import com.d4rk.androidtutorials.data.core.datastore.DataStoreCoreManager
import com.d4rk.androidtutorials.data.database.AppDatabase
import com.d4rk.androidtutorials.data.database.MIGRATION_1_2
//import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppCoreManager : MultiDexApplication() , Application.ActivityLifecycleCallbacks ,
    LifecycleObserver {

    private val dataStoreCoreManager : DataStoreCoreManager = DataStoreCoreManager(context = this)
    private val adsCoreManager : AdsCoreManager = AdsCoreManager(context = this)

    private enum class AppInitializationStage {
        DATA_STORE , ADS
    }

    private var currentStage = AppInitializationStage.DATA_STORE
    private var isAppLoaded = false

    companion object {
        lateinit var database : AppDatabase

        @SuppressLint("StaticFieldLeak")
        lateinit var instance : AppCoreManager
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(observer = this)
        database = Room.databaseBuilder(this , AppDatabase::class.java , "Android Studio Tutorials")
                .addMigrations(MIGRATION_1_2) // Add the migration here
                .fallbackToDestructiveMigration() // Add this if you want destructive migrations in case of future changes
                .build()
        CoroutineScope(Dispatchers.Main).launch {
            if (dataStoreCoreManager.initializeDataStore()) {
                proceedToNextStage()
            }
        }
    }

    private fun proceedToNextStage() {
        when (currentStage) {
            AppInitializationStage.DATA_STORE -> {
                currentStage = AppInitializationStage.ADS
                adsCoreManager.setDataStore(dataStoreCoreManager.dataStore)
                adsCoreManager.initializeAds()
                markAppAsLoaded()
            }

            else -> {
                // All stages completed
            }
        }
    }

    fun isAppLoaded() : Boolean {
        return isAppLoaded
    }

    private fun markAppAsLoaded() {
        isAppLoaded = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        currentActivity?.let { adsCoreManager.showAdIfAvailable(it) }
    }

    private var currentActivity : Activity? = null

    override fun onActivityCreated(activity : Activity , savedInstanceState : Bundle?) {}
    override fun onActivityStarted(activity : Activity) {
        if (! adsCoreManager.isShowingAd) {
            currentActivity = activity
        }
    }

    override fun onActivityResumed(activity : Activity) {}
    override fun onActivityPaused(activity : Activity) {}
    override fun onActivityStopped(activity : Activity) {}
    override fun onActivitySaveInstanceState(activity : Activity , outState : Bundle) {}
    override fun onActivityDestroyed(activity : Activity) {}
}