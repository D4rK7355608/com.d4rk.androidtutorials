package com.d4rk.androidtutorials.utils.error

import android.app.Activity
import android.content.Context
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.constants.error.ErrorType
import com.google.android.material.snackbar.Snackbar

object ErrorHandler {
    fun handleError(applicationContext : Context , errorType : ErrorType) {
        val message : String = when (errorType) {
            ErrorType.SECURITY_EXCEPTION -> applicationContext.getString(R.string.security_error)
            ErrorType.IO_EXCEPTION -> applicationContext.getString(R.string.io_error)
            ErrorType.ACTIVITY_NOT_FOUND -> applicationContext.getString(R.string.activity_not_found)
            ErrorType.ILLEGAL_ARGUMENT -> applicationContext.getString(R.string.illegal_argument_error)
            else -> applicationContext.getString(R.string.unknown_error)
        }

        (applicationContext as? Activity)?.let { activity ->
            activity.runOnUiThread {
                Snackbar.make(
                    activity.findViewById(android.R.id.content) , message , Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}