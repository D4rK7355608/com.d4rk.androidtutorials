package com.d4rk.androidtutorials.utils.constants.ads

import com.d4rk.androidtutorials.BuildConfig

object AdsConstants {

    val BANNER_AD_UNIT_ID : String
        get() = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/6300978111"
        }
        else {
            "ca-app-pub-5294151573817700/8040893463"
        }

    val APP_OPEN_UNIT_ID : String
        get() = if (BuildConfig.DEBUG) {
            "ca-app-pub-3940256099942544/9257395921"
        }
        else {
            "ca-app-pub-5294151573817700/9208287867"
        }
}