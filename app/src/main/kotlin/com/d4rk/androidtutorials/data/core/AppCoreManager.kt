@file:Suppress("DEPRECATION")

package com.d4rk.androidtutorials.data.core

import android.annotation.SuppressLint
import android.app.Activity
import android.database.sqlite.SQLiteException
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.room.Room
import androidx.room.migration.Migration
import com.d4rk.android.libs.apptoolkit.data.core.BaseCoreManager
import com.d4rk.android.libs.apptoolkit.data.core.ads.AdsCoreManager
import com.d4rk.android.libs.apptoolkit.utils.error.ErrorHandler
import com.d4rk.android.libs.apptoolkit.utils.error.ErrorHandler.handleInitializationFailure
import com.d4rk.androidtutorials.data.database.AppDatabase
import com.d4rk.androidtutorials.data.database.migrations.MIGRATION_1_2
import com.d4rk.androidtutorials.data.database.migrations.MIGRATION_2_3
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.utils.constants.ads.AdsConstants
import com.d4rk.androidtutorials.utils.error.CrashlyticsErrorReporter
import io.ktor.client.HttpClient
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.supervisorScope

class AppCoreManager : BaseCoreManager() {

    private var currentActivity : Activity? = null

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance : AppCoreManager
            private set

        lateinit var dataStore : DataStore
            private set

        lateinit var database : AppDatabase
            private set

        val adsCoreManager : AdsCoreManager by lazy {
            AdsCoreManager(context = instance)
        }

        val ktorClient : HttpClient
            get() = BaseCoreManager.ktorClient

        val isAppLoaded : Boolean
            get() = BaseCoreManager.isAppLoaded
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val crashlyticsReporter = CrashlyticsErrorReporter()
        ErrorHandler.init(reporter = crashlyticsReporter)

        registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(observer = this)
    }

    override suspend fun onInitializeApp() = supervisorScope {
        val dataStoreInitialization : Deferred<Unit> = async { initializeDataStore() }
        val databaseInitialization : Deferred<Unit> = async { initializeDatabase() }
        val adsInitialization : Deferred<Unit> = async { initializeAds() }

        dataStoreInitialization.await()
        databaseInitialization.await()
        adsInitialization.await()
    }

    private fun initializeDataStore() {
        runCatching {
            dataStore = DataStore.getInstance(context = this@AppCoreManager)
        }.onFailure {
            handleInitializationFailure(
                message = "DataStore initialization failed" , exception = it as Exception , applicationContext = applicationContext
            )
        }
    }

    private suspend fun initializeDatabase() {
        runCatching {
            database = Room.databaseBuilder(
                context = this@AppCoreManager , klass = AppDatabase::class.java , name = "Android Studio Tutorials"
            ).addMigrations(migrations = getMigrations()).fallbackToDestructiveMigration().fallbackToDestructiveMigrationOnDowngrade().build()

            database.openHelper.writableDatabase
        }.onFailure {
            handleDatabaseError(exception = it as Exception)
        }
    }

    private suspend fun handleDatabaseError(exception : Exception) {
        if (exception is SQLiteException || (exception is IllegalStateException && exception.message?.contains(other = "Migration failed") == true)) {
            eraseDatabase()
        }
    }

    private suspend fun eraseDatabase() {
        runCatching {
            deleteDatabase("Android Studio Tutorials")
        }.onSuccess {
            initializeDatabase()
        }.onFailure {
            logDatabaseError(exception = it as Exception)
        }
    }

    private fun logDatabaseError(exception : Exception) {
        Log.e("AppCoreManager" , "Database error: ${exception.message}" , exception)
    }

    private fun initializeAds() {
        adsCoreManager.initializeAds(AdsConstants.APP_OPEN_UNIT_ID)
    }

    private fun getMigrations() : Array<Migration> {
        return arrayOf(
            MIGRATION_1_2 , MIGRATION_2_3
        )
    }

    fun isAppLoaded() : Boolean {
        return isAppLoaded
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onMoveToForeground() {
        currentActivity?.let { adsCoreManager.showAdIfAvailable(it) }
    }

    override fun onActivityCreated(activity : Activity , savedInstanceState : Bundle?) {}

    override fun onActivityStarted(activity : Activity) {
        currentActivity = activity
    }

    override fun onActivityResumed(activity : Activity) {}
    override fun onActivityPaused(activity : Activity) {}
    override fun onActivityStopped(activity : Activity) {}
    override fun onActivitySaveInstanceState(activity : Activity , outState : Bundle) {}
    override fun onActivityDestroyed(activity : Activity) {}
}