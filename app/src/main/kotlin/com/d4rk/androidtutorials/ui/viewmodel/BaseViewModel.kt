package com.d4rk.androidtutorials.ui.viewmodel

import android.app.Application
import android.content.ActivityNotFoundException
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.constants.error.ErrorType
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.utils.error.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    private val _isLoading = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _uiErrorModel = MutableStateFlow(UiErrorModel())
    val uiErrorModel: StateFlow<UiErrorModel> = _uiErrorModel.asStateFlow()

    protected val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.e("BaseViewModel", "Coroutine Exception: ", exception)
        handleError(exception)
    }

    private fun handleError(exception: Throwable) {
        val errorType: ErrorType = when (exception) {
            is SecurityException -> ErrorType.SECURITY_EXCEPTION
            is IOException -> ErrorType.IO_EXCEPTION
            is ActivityNotFoundException -> ErrorType.ACTIVITY_NOT_FOUND
            is IllegalArgumentException -> ErrorType.ILLEGAL_ARGUMENT
            else -> ErrorType.UNKNOWN_ERROR
        }
        handleError(errorType, exception)

        _uiErrorModel.value = UiErrorModel(
            showErrorDialog = true, errorMessage = when (errorType) {
                ErrorType.SECURITY_EXCEPTION -> getApplication<Application>().getString(R.string.security_error)
                ErrorType.IO_EXCEPTION -> getApplication<Application>().getString(R.string.io_error)
                ErrorType.ACTIVITY_NOT_FOUND -> getApplication<Application>().getString(R.string.activity_not_found)
                ErrorType.ILLEGAL_ARGUMENT -> getApplication<Application>().getString(R.string.illegal_argument_error)
                ErrorType.UNKNOWN_ERROR -> getApplication<Application>().getString(R.string.unknown_error)
            }
        )
    }

    fun dismissErrorDialog() {
        _uiErrorModel.value = UiErrorModel(showErrorDialog = false)
    }

    protected open fun handleError(errorType: ErrorType, ignoredException: Throwable) {
        ErrorHandler.handleError(getApplication(), errorType)
    }

    protected fun showLoading() {
        _isLoading.value = true
    }

    protected fun hideLoading() {
        _isLoading.value = false
    }
}