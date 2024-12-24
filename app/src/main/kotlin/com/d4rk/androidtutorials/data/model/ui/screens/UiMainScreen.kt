package com.d4rk.androidtutorials.data.model.ui.screens

import com.d4rk.androidtutorials.data.model.ui.navigation.BottomNavigationScreen
import com.d4rk.androidtutorials.data.model.ui.navigation.NavigationDrawerItem

data class UiMainScreen(
    val navigationDrawerItems : List<NavigationDrawerItem> = listOf() ,
    val bottomNavigationItems : List<BottomNavigationScreen> = listOf() ,
    val currentBottomNavigationScreen : BottomNavigationScreen = BottomNavigationScreen.Home ,
)