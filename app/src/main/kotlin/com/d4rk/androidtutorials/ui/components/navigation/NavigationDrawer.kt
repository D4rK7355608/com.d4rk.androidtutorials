package com.d4rk.androidtutorials.ui.components.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.d4rk.android.libs.apptoolkit.data.model.ui.navigation.NavigationDrawerItem
import com.d4rk.android.libs.apptoolkit.ui.components.modifiers.bounceClick
import com.d4rk.android.libs.apptoolkit.ui.components.modifiers.hapticDrawerSwipe
import com.d4rk.android.libs.apptoolkit.ui.components.spacers.LargeVerticalSpacer
import com.d4rk.android.libs.apptoolkit.utils.helpers.IntentsHelper
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.model.ui.screens.MainScreenState
import com.d4rk.androidtutorials.ui.screens.help.HelpActivity
import com.d4rk.androidtutorials.ui.screens.main.MainScaffoldContent
import com.d4rk.androidtutorials.ui.screens.settings.SettingsActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer(
    mainScreenState : MainScreenState
) {
    val uiState by mainScreenState.viewModel.uiState.collectAsState()
    val drawerItems = uiState.navigationDrawerItems
    val coroutineScope : CoroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(modifier = Modifier.hapticDrawerSwipe(drawerState = mainScreenState.drawerState) , drawerState = mainScreenState.drawerState , drawerContent = {
        ModalDrawerSheet {
            LargeVerticalSpacer()
            drawerItems.forEach { item ->
                NavigationDrawerItemContent(
                    item = item , coroutineScope = coroutineScope , drawerState = mainScreenState.drawerState , context = mainScreenState.context
                )
            }
        }
    }) {
        MainScaffoldContent(
            mainScreenState = mainScreenState , coroutineScope = coroutineScope
        )
    }
}


@Composable
private fun NavigationDrawerItemContent(
    item : NavigationDrawerItem , coroutineScope : CoroutineScope , drawerState : DrawerState , context : Context
) {
    val title = stringResource(id = item.title)
    NavigationDrawerItem(label = { Text(text = title) } , selected = false , onClick = {
        when (item.title) {
            R.string.settings -> {
                IntentsHelper.openActivity(
                    context = context , activityClass = SettingsActivity::class.java
                )
            }

            R.string.help_and_feedback -> {
                IntentsHelper.openActivity(
                    context = context , activityClass = HelpActivity::class.java
                )
            }

            R.string.updates -> {
                IntentsHelper.openUrl(
                    context = context , url = "https://github.com/D4rK7355608/${context.packageName}/blob/master/CHANGELOG.md"
                )
            }

            R.string.share -> {
                IntentsHelper.shareApp(
                    context = context , shareMessageFormat = R.string.summary_share_message
                )
            }
        }
        coroutineScope.launch { drawerState.close() }
    } , icon = {
        Icon(item.selectedIcon , contentDescription = title)
    } , badge = {
        if (item.badgeText.isNotBlank()) {
            Text(text = item.badgeText)
        }
    } , modifier = Modifier
            .padding(paddingValues = NavigationDrawerItemDefaults.ItemPadding)
            .bounceClick())
}