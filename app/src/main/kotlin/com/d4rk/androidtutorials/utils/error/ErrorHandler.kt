package com.d4rk.androidtutorials.utils.error

import android.app.Activity
import android.content.Context
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.utils.constants.error.ErrorType
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.crashlytics.FirebaseCrashlytics

object ErrorHandler {

    private val crashlytics : FirebaseCrashlytics = FirebaseCrashlytics.getInstance()

    fun handleError(applicationContext : Context , errorType : ErrorType) {
        val message : String = when (errorType) {
            ErrorType.SECURITY_EXCEPTION -> applicationContext.getString(R.string.security_error)
            ErrorType.IO_EXCEPTION -> applicationContext.getString(R.string.io_error)
            ErrorType.ACTIVITY_NOT_FOUND -> applicationContext.getString(R.string.activity_not_found)
            ErrorType.ILLEGAL_ARGUMENT -> applicationContext.getString(R.string.illegal_argument_error)
            else -> applicationContext.getString(R.string.unknown_error)
        }

        crashlytics.apply {
            setCustomKey("error_type" , errorType.name)
            setCustomKey("error_message" , message)
            recordException(Exception("$errorType: $message"))
        }

        showSnackbar(context = applicationContext , message = message)
    }

    fun handleInitializationFailure(
        applicationContext : Context , message : String , exception : Exception? = null
    ) {
        val displayMessage : String = message.ifEmpty {
            applicationContext.getString(R.string.initialization_error)
        }

        crashlytics.apply {
            setCustomKey("error_type" , "INITIALIZATION_FAILURE")
            setCustomKey("error_message" , displayMessage)
            exception?.let { recordException(it) }
                ?: recordException(Exception("Initialization Failure: $displayMessage"))
        }

        showSnackbar(context = applicationContext , message = displayMessage)
    }

    private fun showSnackbar(context : Context , message : String) {
        (context as? Activity)?.let { activity ->
            activity.runOnUiThread {
                Snackbar.make(
                    activity.findViewById(android.R.id.content) , message , Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}