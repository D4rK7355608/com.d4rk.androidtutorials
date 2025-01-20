package com.d4rk.androidtutorials.data.model.ui.screens

import android.content.Context
import android.view.View
import androidx.compose.material3.DrawerState
import androidx.navigation.NavHostController
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.ui.screens.main.MainViewModel

data class MainScreenState(
    val context: Context ,
    val view: View ,
    val drawerState: DrawerState ,
    val navHostController: NavHostController ,
    val dataStore: DataStore ,
    val viewModel: MainViewModel
)