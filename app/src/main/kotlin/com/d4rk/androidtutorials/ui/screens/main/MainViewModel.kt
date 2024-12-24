package com.d4rk.androidtutorials.ui.screens.main

import android.app.Activity
import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.navigation.BottomNavigationScreen
import com.d4rk.androidtutorials.data.model.ui.screens.UiMainScreen
import com.d4rk.androidtutorials.notifications.managers.AppUpdateNotificationsManager
import com.d4rk.androidtutorials.ui.screens.main.repository.MainRepository
import com.d4rk.androidtutorials.ui.screens.startup.StartupActivity
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import com.d4rk.androidtutorials.utils.helpers.IntentsHelper
import com.google.android.play.core.appupdate.AppUpdateManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(application : Application) : BaseViewModel(application) {
    private val repository = MainRepository(DataStore(application) , application)
    private val _uiState : MutableStateFlow<UiMainScreen> = MutableStateFlow(UiMainScreen())
    val uiState : StateFlow<UiMainScreen> = _uiState

    fun checkForUpdates(activity : Activity , appUpdateManager : AppUpdateManager) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.checkForUpdates(
                appUpdateManager = appUpdateManager , activity = activity
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkAndScheduleUpdateNotifications(appUpdateNotificationsManager : AppUpdateNotificationsManager) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.checkAndScheduleUpdateNotificationsRepository(appUpdateNotificationsManager = appUpdateNotificationsManager)
        }
    }

    fun checkAppUsageNotifications() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.checkAppUsageNotificationsRepository()
        }
    }

    fun checkAndHandleStartup() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.checkAndHandleStartupRepository { isFirstTime ->
                if (isFirstTime) {
                    IntentsHelper.openActivity(
                        context = getApplication() ,
                        activityClass = StartupActivity::class.java
                    )
                }
            }
        }
    }

    fun configureSettings() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            repository.setupSettingsRepository()
        }
    }

    fun updateBottomNavigationScreen(newScreen: BottomNavigationScreen) {
        _uiState.value = _uiState.value.copy(currentBottomNavigationScreen = newScreen)
    }
}