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
import com.d4rk.androidtutorials.utils.constants.ui.bottombar.BottomBarRoutes

sealed class BottomNavigationScreen(
    val route : String , val icon : ImageVector , val selectedIcon : ImageVector , val title : Int
) {
    data object Home : BottomNavigationScreen(
        route = BottomBarRoutes.HOME ,
        icon = Icons.Outlined.Home ,
        selectedIcon = Icons.Filled.Home ,
        title = com.d4rk.android.libs.apptoolkit.R.string.home
    )

    data object StudioBot : BottomNavigationScreen(
        route = BottomBarRoutes.STUDIO_BOT ,
        icon = Icons.Sharp.AutoAwesome ,
        selectedIcon = Icons.Rounded.AutoAwesome ,
        title = R.string.studio_bot
    )

    data object Favorites : BottomNavigationScreen(
        route = BottomBarRoutes.FAVORITES ,
        icon = Icons.Sharp.FavoriteBorder ,
        selectedIcon = Icons.Rounded.Favorite ,
        title = R.string.favorites
    )
}