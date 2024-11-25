package com.d4rk.androidtutorials.utils.helpers

import androidx.annotation.StringRes
import com.d4rk.androidtutorials.data.core.AppCoreManager

fun getStringResource(@StringRes id : Int) : String {
    return AppCoreManager.instance.getString(id)
}