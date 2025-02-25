package com.d4rk.androidtutorials.ui.components.ads

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.d4rk.androidtutorials.data.core.AppCoreManager
import com.d4rk.androidtutorials.utils.constants.ads.AdsConstants
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@Composable
fun AdBanner(modifier : Modifier = Modifier , adSize : AdSize = AdSize.BANNER) {
    val showAds : Boolean by AppCoreManager.dataStore.ads.collectAsState(initial = true)

    if (showAds) {
        AndroidView(modifier = modifier
                .fillMaxWidth()
                .height(height = adSize.height.dp) , factory = { context ->
            AdView(context).apply {
                setAdSize(adSize)
                adUnitId = AdsConstants.BANNER_AD_UNIT_ID
                loadAd(AdRequest.Builder().build())
            }
        })
    }
}