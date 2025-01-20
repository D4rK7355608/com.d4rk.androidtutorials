package com.d4rk.androidtutorials.ui.screens.main

import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.navigation.compose.rememberNavController
import com.d4rk.android.libs.apptoolkit.utils.helpers.ScreenHelper
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.data.model.ui.screens.MainScreenState
import com.d4rk.androidtutorials.ui.components.navigation.BottomNavigationBar
import com.d4rk.androidtutorials.ui.components.navigation.NavigationDrawer
import com.d4rk.androidtutorials.ui.components.navigation.NavigationHost
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarMain
import com.d4rk.androidtutorials.utils.constants.ui.DrawerStyle
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainScreen(viewModel : MainViewModel) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val navController = rememberNavController()
    val context = LocalContext.current
    val view = LocalView.current
    val dataStore = AppCoreManager.dataStore

    val mainScreenState = remember {
        MainScreenState(
            context = context , view = view , drawerState = drawerState , navHostController = navController , dataStore = dataStore , viewModel = viewModel
        )
    }

    val isTabletOrLandscape = ScreenHelper.isLandscapeOrTablet(context)
    val drawerStyle = if (isTabletOrLandscape) DrawerStyle.PERMANENT else DrawerStyle.MODAL

    NavigationDrawer(
        style = drawerStyle , mainScreenState = mainScreenState
    )
}

@Composable
fun MainScaffoldContent(
    mainScreenState : MainScreenState , coroutineScope : CoroutineScope
) {
    Scaffold(modifier = Modifier.imePadding() , topBar = {
        TopAppBarMain(
            view = mainScreenState.view , drawerState = mainScreenState.drawerState , context = mainScreenState.context , coroutineScope = coroutineScope
        )
    } , bottomBar = {
        BottomNavigationBar(
            navController = mainScreenState.navHostController , dataStore = mainScreenState.dataStore , view = mainScreenState.view , viewModel = mainScreenState.viewModel
        )
    }) { paddingValues ->
        NavigationHost(
            navHostController = mainScreenState.navHostController , dataStore = mainScreenState.dataStore , paddingValues = paddingValues
        )
    }
}