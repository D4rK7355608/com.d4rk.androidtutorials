package com.d4rk.androidtutorials.data.model.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.sharp.AutoAwesome
import androidx.compose.material.icons.sharp.FavoriteBorder
import androidx.compose.ui.graphics.vector.ImageVector
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.constants.ui.bottombar.BottomBarRoutes

sealed class BottomNavigationScreen(
    val route : String , val icon : ImageVector , val selectedIcon : ImageVector , val title : Int
) {
    data object Home : BottomNavigationScreen(
        BottomBarRoutes.HOME , Icons.Outlined.Home , Icons.Filled.Home , R.string.home
    )

    data object StudioBot : BottomNavigationScreen(
        BottomBarRoutes.STUDIO_BOT ,
        Icons.Sharp.AutoAwesome ,
        Icons.Rounded.AutoAwesome ,
        R.string.studio_bot
    )

    data object Favorites : BottomNavigationScreen(
        BottomBarRoutes.FAVORITES ,
        Icons.Sharp.FavoriteBorder ,
        Icons.Rounded.Favorite ,
        R.string.favorites
    )
}