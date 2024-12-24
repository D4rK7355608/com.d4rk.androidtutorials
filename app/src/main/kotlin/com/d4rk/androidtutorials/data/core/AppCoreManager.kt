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
import com.d4rk.androidtutorials.data.client.KtorClient
import com.d4rk.androidtutorials.data.core.ads.AdsCoreManager
import com.d4rk.androidtutorials.data.core.datastore.DataStoreCoreManager
import com.d4rk.androidtutorials.data.database.AppDatabase
import com.d4rk.androidtutorials.data.database.MIGRATION_1_2
import io.ktor.client.HttpClient
//import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppCoreManager : MultiDexApplication() , Application.ActivityLifecycleCallbacks ,
    LifecycleObserver {

    private val dataStoreCoreManager : DataStoreCoreManager = DataStoreCoreManager(context = this)
    private val adsCoreManager : AdsCoreManager = AdsCoreManager(context = this)

    private enum class AppInitializationStage {
        DATA_STORE , ADS , FINALIZATION
    }

    private var currentStage = AppInitializationStage.DATA_STORE
    private var isAppLoaded = false

    companion object {
        lateinit var database : AppDatabase

        @SuppressLint("StaticFieldLeak")
        lateinit var instance : AppCoreManager
            private set
        lateinit var ktorClient : HttpClient
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        ktorClient = KtorClient().createClient()
        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(observer = this)
        try {
            initializeDatabase()
        } catch (e : Exception) {
            handleDatabaseError(e)
        }
        CoroutineScope(Dispatchers.Main).launch {
            if (dataStoreCoreManager.initializeDataStore()) {
                proceedToNextStage()
            }
        }
    }

    private fun proceedToNextStage() {
        currentStage = when (currentStage) {
            AppInitializationStage.DATA_STORE -> {
                adsCoreManager.setDataStore(dataStoreCoreManager.dataStore)
                AppInitializationStage.ADS
            }

            AppInitializationStage.ADS -> {
                adsCoreManager.initializeAds()
                AppInitializationStage.FINALIZATION
            }

            AppInitializationStage.FINALIZATION -> {
                markAppAsLoaded()
                return
            }
        }
        proceedToNextStage()
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

    private fun initializeDatabase() {
        database = Room.databaseBuilder(
            context = this , klass = AppDatabase::class.java , name = "Android Studio Tutorials"
        ).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration()
                .fallbackToDestructiveMigrationOnDowngrade().build()
    }

    private fun handleDatabaseError(exception : Exception) {
        (exception as? IllegalStateException)
                ?.takeIf { it.message?.contains(other = "Migration failed") == true }
                ?.let { eraseDatabase() }
    }

    private fun eraseDatabase() {
        runCatching {
            val databaseName = "Android Studio Tutorials"
            getDatabasePath(databaseName).delete()
        }.onSuccess {
            initializeDatabase()
        }
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