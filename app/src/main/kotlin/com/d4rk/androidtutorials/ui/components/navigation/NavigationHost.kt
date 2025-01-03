package com.d4rk.androidtutorials.ui.components.navigation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.d4rk.androidtutorials.utils.constants.ui.bottombar.BottomBarRoutes
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.navigation.BottomNavigationScreen
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson
import com.d4rk.androidtutorials.ui.screens.favorites.FavoritesScreen
import com.d4rk.androidtutorials.ui.screens.home.HomeScreen
import com.d4rk.androidtutorials.ui.screens.studiobot.StudioBotScreen

@Composable
fun NavigationHost(
    navHostController : NavHostController ,
    dataStore : DataStore ,
    paddingValues : PaddingValues ,
) {
    val startupPage : String =
            dataStore.getStartupPage().collectAsState(initial = BottomBarRoutes.HOME).value

    NavHost(navController = navHostController , startDestination = startupPage) {
        composable(route = BottomNavigationScreen.Home.route) {
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                HomeScreen()
            }
        }
        composable(route = BottomNavigationScreen.StudioBot.route) {
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                StudioBotScreen()
            }
        }
        composable(route = BottomNavigationScreen.Favorites.route) {
            Box(modifier = Modifier.padding(paddingValues = paddingValues)) {
                FavoritesScreen()
            }
        }
    }
}

fun openLessonDetailActivity(context : Context , lesson : UiHomeLesson) {
    Intent(Intent.ACTION_VIEW , Uri.parse(lesson.deepLinkPath)).let { intent ->
        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
        }
    }
}