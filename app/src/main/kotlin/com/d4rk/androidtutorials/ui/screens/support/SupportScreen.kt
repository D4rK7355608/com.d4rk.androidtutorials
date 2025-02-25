@file:Suppress("DEPRECATION")

package com.d4rk.androidtutorials.ui.screens.support

import android.content.Context
import android.view.SoundEffectConstants
import android.view.View
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Paid
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.d4rk.android.libs.apptoolkit.ui.components.modifiers.bounceClick
import com.d4rk.android.libs.apptoolkit.ui.components.spacers.ButtonIconSpacer
import com.d4rk.android.libs.apptoolkit.utils.helpers.IntentsHelper
import com.d4rk.androidtutorials.ui.components.ads.AdBanner
import com.d4rk.androidtutorials.ui.components.navigation.TopAppBarScaffoldWithBackButton
import com.google.android.gms.ads.AdSize

@Composable
fun SupportComposable(viewModel : SupportViewModel , activity : SupportActivity) {
    val context : Context = LocalContext.current
    val view : View = LocalView.current
    val billingClient : BillingClient = rememberBillingClient(context , viewModel)
    TopAppBarScaffoldWithBackButton(title = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.support_us) , onBackClicked = { activity.finish() }) { paddingValues ->
        Box(
            modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxHeight()
        ) {
            LazyColumn {
                item {
                    Text(
                        text = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.paid_support) ,
                        modifier = Modifier.padding(start = 16.dp , top = 16.dp) ,
                        style = MaterialTheme.typography.titleLarge ,
                    )
                }
                item {
                    OutlinedCard(
                        modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                    ) {
                        Column {
                            Text(
                                text = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.summary_donations) , modifier = Modifier.padding(16.dp)
                            )
                            LazyRow(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 16.dp) , horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                item {
                                    FilledTonalButton(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .bounceClick() ,
                                        onClick = {
                                            view.playSoundEffect(SoundEffectConstants.CLICK)
                                            activity.initiatePurchase(
                                                sku = "low_donation" ,
                                                viewModel.skuDetails ,
                                                billingClient ,
                                            )
                                        } ,
                                    ) {
                                        Icon(
                                            Icons.Outlined.Paid , contentDescription = null , modifier = Modifier.size(ButtonDefaults.IconSize)
                                        )
                                        ButtonIconSpacer()
                                        Text(
                                            text = viewModel.skuDetails["low_donation"]?.price ?: ""
                                        )
                                    }
                                }
                                item {
                                    FilledTonalButton(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .bounceClick() ,
                                        onClick = {
                                            view.playSoundEffect(SoundEffectConstants.CLICK)
                                            activity.initiatePurchase(
                                                sku = "normal_donation" ,
                                                viewModel.skuDetails ,
                                                billingClient ,
                                            )
                                        } ,
                                    ) {
                                        Icon(
                                            Icons.Outlined.Paid , contentDescription = null , modifier = Modifier.size(ButtonDefaults.IconSize)
                                        )
                                        ButtonIconSpacer()
                                        Text(
                                            text = viewModel.skuDetails["normal_donation"]?.price ?: ""
                                        )
                                    }
                                }
                            }
                            LazyRow(
                                modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp) , horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                item {
                                    FilledTonalButton(
                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .bounceClick() ,
                                        onClick = {
                                            view.playSoundEffect(SoundEffectConstants.CLICK)
                                            activity.initiatePurchase(
                                                sku = "high_donation" ,
                                                viewModel.skuDetails ,
                                                billingClient ,
                                            )
                                        } ,
                                    ) {
                                        Icon(
                                            Icons.Outlined.Paid , contentDescription = null , modifier = Modifier.size(ButtonDefaults.IconSize)
                                        )
                                        ButtonIconSpacer()
                                        Text(
                                            text = viewModel.skuDetails["high_donation"]?.price ?: ""
                                        )
                                    }
                                }
                                item {
                                    FilledTonalButton(

                                        modifier = Modifier
                                                .fillMaxWidth()
                                                .bounceClick() ,
                                        onClick = {
                                            view.playSoundEffect(SoundEffectConstants.CLICK)
                                            activity.initiatePurchase(
                                                sku = "extreme_donation" ,
                                                viewModel.skuDetails ,
                                                billingClient ,
                                            )
                                        } ,
                                    ) {
                                        Icon(
                                            Icons.Outlined.Paid , contentDescription = null , modifier = Modifier.size(ButtonDefaults.IconSize)
                                        )
                                        ButtonIconSpacer()
                                        Text(
                                            text = viewModel.skuDetails["extreme_donation"]?.price ?: ""
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
                item {
                    Text(
                        text = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.non_paid_support) ,
                        modifier = Modifier.padding(start = 16.dp) ,
                        style = MaterialTheme.typography.titleLarge ,
                    )
                }
                item {
                    FilledTonalButton(
                        onClick = {
                            view.playSoundEffect(SoundEffectConstants.CLICK)
                            IntentsHelper.openUrl(
                                context = context , url = "https://direct-link.net/548212/agOqI7123501341"
                            )
                        } ,
                        modifier = Modifier
                                .fillMaxWidth()
                                .bounceClick()
                                .padding(16.dp) ,
                    ) {
                        Icon(
                            Icons.Outlined.Paid , contentDescription = null , modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        ButtonIconSpacer()
                        Text(text = stringResource(id = com.d4rk.android.libs.apptoolkit.R.string.web_ad))
                    }
                }
                item {
                    AdBanner(modifier = Modifier.padding(bottom = 12.dp) , adSize = AdSize.LARGE_BANNER)
                }
            }
        }
    }
}

@Composable
fun rememberBillingClient(
    context : Context , viewModel : SupportViewModel
) : BillingClient {
    val billingClient : BillingClient = remember {
        BillingClient.newBuilder(context).setListener { _ , _ -> }.enablePendingPurchases().build()
    }

    DisposableEffect(billingClient) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult : BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    viewModel.querySkuDetails(billingClient)
                }
            }

            override fun onBillingServiceDisconnected() {}
        })

        onDispose {
            billingClient.endConnection()
        }
    }
    return billingClient
}