package com.d4rk.androidtutorials.ui.components.navigation

import android.view.SoundEffectConstants
import android.view.View
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.navigation.BottomNavigationScreen
import com.d4rk.androidtutorials.ui.components.ads.AdBannerFull
import com.d4rk.androidtutorials.ui.components.modifiers.bounceClick

@Composable
fun BottomNavigationBar(
    navController : NavController ,
    dataStore : DataStore ,
    view : View ,
    currentScreen : BottomNavigationScreen ,
    onScreenSelected : (BottomNavigationScreen) -> Unit
) {
    val bottomBarItems : List<BottomNavigationScreen> = listOf(
        BottomNavigationScreen.Home ,
        BottomNavigationScreen.StudioBot ,
        BottomNavigationScreen.Favorites
    )
    val showLabels : Boolean =
            dataStore.getShowBottomBarLabels().collectAsState(initial = true).value

    Column {
        AdBannerFull(
            modifier = Modifier.fillMaxWidth() , dataStore = dataStore
        )
        NavigationBar {
            bottomBarItems.forEach { screen ->
                NavigationBarItem(icon = {
                    val iconResource : ImageVector =
                            if (currentScreen == screen) screen.selectedIcon else screen.icon
                    Icon(
                        imageVector = iconResource ,
                        modifier = Modifier.bounceClick() ,
                        contentDescription = null
                    )
                } , label = {
                    if (showLabels) Text(
                        text = stringResource(id = screen.title) ,
                        overflow = TextOverflow.Ellipsis ,
                        modifier = Modifier.basicMarquee()
                    )
                } , selected = currentScreen == screen , onClick = {
                    view.playSoundEffect(SoundEffectConstants.CLICK)
                    if (currentScreen != screen) {
                        onScreenSelected(screen)
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = false
                            }
                            launchSingleTop = true
                        }
                    }
                })
            }
        }
    }
}