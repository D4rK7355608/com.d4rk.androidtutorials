package com.d4rk.androidtutorials.ui.screens.main

import android.content.Context
import android.view.View
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.ui.components.navigation.BottomNavigationBar
import com.d4rk.androidtutorials.ui.components.navigation.NavigationDrawer
import com.d4rk.androidtutorials.ui.components.navigation.NavigationHost
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarMain
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainScreen() {
    val drawerState : DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navController : NavHostController = rememberNavController()
    val context : Context = LocalContext.current
    val view : View = LocalView.current
    val dataStore : DataStore = DataStore.getInstance(context = context)

    NavigationDrawer(
        navHostController = navController ,
        drawerState = drawerState ,
        view = view ,
        dataStore = dataStore ,
        context = context ,
    )
}

@Composable
fun MainScreenContent(
    view : View ,
    drawerState : DrawerState ,
    context : Context ,
    coroutineScope : CoroutineScope ,
    navHostController : NavHostController ,
    dataStore : DataStore
) {
    Scaffold(modifier = Modifier.imePadding() , topBar = {
        TopAppBarMain(
            view = view ,
            drawerState = drawerState ,
            context = context ,
            coroutineScope = coroutineScope
        )
    } , bottomBar = {
        BottomNavigationBar(navHostController , dataStore , view)
    }) { paddingValues ->
        NavigationHost(
            navHostController = navHostController ,
            dataStore = dataStore ,
            paddingValues = paddingValues
        )
    }
}