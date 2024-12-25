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
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.utils.constants.core.AppInitializationStages
import com.d4rk.androidtutorials.utils.error.ErrorHandler.handleInitializationFailure
import io.ktor.client.HttpClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppCoreManager : MultiDexApplication() , Application.ActivityLifecycleCallbacks , LifecycleObserver {

    private val dataStoreCoreManager by lazy {
        DataStoreCoreManager(context = this)
    }

    private val adsCoreManager by lazy {
        AdsCoreManager(context = this)
    }

    private var currentActivity : Activity? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(observer = this)
        CoroutineScope(context = Dispatchers.IO).launch {
            initializeApp()
        }
    }

    private suspend fun initializeApp() {
        initializeKtorClient()
    }

    private suspend fun initializeKtorClient() {
        runCatching {
            ktorClient = withContext(context = Dispatchers.IO) {
                KtorClient().createClient()
            }
        }.onSuccess {
            proceedToNextStage(stage = AppInitializationStages.DATABASE)
        }.onFailure {
            handleInitializationFailure(
                message = "Ktor client initialization failed" ,
                exception = it as Exception ,
                applicationContext = applicationContext
            )
        }
    }

    private suspend fun initializeDatabase() {
        runCatching {
            withContext(context = Dispatchers.IO) {
                database = Room.databaseBuilder(
                    context = this@AppCoreManager ,
                    klass = AppDatabase::class.java ,
                    name = "Android Studio Tutorials"
                ).addMigrations(MIGRATION_1_2).fallbackToDestructiveMigration()
                        .fallbackToDestructiveMigrationOnDowngrade().build()
            }
        }.onSuccess {
            proceedToNextStage(stage = AppInitializationStages.DATA_STORE)
        }.onFailure {
            handleDatabaseError(exception = it as Exception)
        }
    }

    private suspend fun initializeDataStore() {
        runCatching {
            withContext(context = Dispatchers.IO) {
                dataStore = DataStore.getInstance(context = this@AppCoreManager)
                dataStoreCoreManager.initializeDataStore()
            }
        }.onSuccess {
            proceedToNextStage(stage = AppInitializationStages.ADS)
        }.onFailure {
            handleInitializationFailure(
                message = "DataStore initialization failed" ,
                exception = it as Exception ,
                applicationContext = applicationContext
            )
        }
    }

    private suspend fun initializeAds() {
        runCatching {
            withContext(context = Dispatchers.IO) {
                adsCoreManager.initializeAds()
            }
        }.onSuccess {
            proceedToNextStage(stage = AppInitializationStages.FINALIZATION)
        }.onFailure {
            handleInitializationFailure(
                message = "Ads initialization failed" ,
                exception = it as Exception ,
                applicationContext = applicationContext
            )
        }
    }

    private fun finalizeInitialization() {
        markAppAsLoaded()
    }

    private suspend fun proceedToNextStage(stage : AppInitializationStages) {
        currentStage = stage
        when (stage) {
            AppInitializationStages.DATABASE -> initializeDatabase()
            AppInitializationStages.DATA_STORE -> initializeDataStore()
            AppInitializationStages.ADS -> initializeAds()
            AppInitializationStages.FINALIZATION -> finalizeInitialization()
            AppInitializationStages.KTOR_CLIENT -> { /*Initialization done in a separate step*/
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        currentActivity?.let { adsCoreManager.showAdIfAvailable(activity = it) }
    }

    private suspend fun handleDatabaseError(exception : Exception) {
        (exception as? IllegalStateException)
                ?.takeIf { it.message?.contains("Migration failed") == true }
                ?.let { eraseDatabase() }
    }

    private suspend fun eraseDatabase() {
        runCatching {
            deleteDatabase("Android Studio Tutorials")
        }.onSuccess {
            initializeDatabase()
        }
    }

    private fun markAppAsLoaded() {
        isAppLoaded = true
    }

    fun isAppLoaded() : Boolean {
        return isAppLoaded
    }

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

    companion object {

        lateinit var dataStore : DataStore
            private set

        lateinit var database : AppDatabase
            private set

        @SuppressLint("StaticFieldLeak")
        lateinit var instance : AppCoreManager
            private set

        lateinit var ktorClient : HttpClient
            private set

        var currentStage : AppInitializationStages = AppInitializationStages.KTOR_CLIENT
            private set

        var isAppLoaded : Boolean = false
            private set
    }
}