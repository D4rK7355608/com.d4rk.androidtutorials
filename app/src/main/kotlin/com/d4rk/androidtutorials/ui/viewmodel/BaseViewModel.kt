package com.d4rk.androidtutorials.ui.viewmodel

import android.app.Application
import android.content.ActivityNotFoundException
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.utils.constants.error.ErrorType
import com.d4rk.androidtutorials.data.model.ui.error.UiErrorModel
import com.d4rk.androidtutorials.utils.error.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException

open class BaseViewModel(application : Application) : AndroidViewModel(application) {
    private val _isLoading : MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isLoading : StateFlow<Boolean> = _isLoading

    private val _uiErrorModel : MutableStateFlow<UiErrorModel> = MutableStateFlow(UiErrorModel())
    val uiErrorModel : StateFlow<UiErrorModel> = _uiErrorModel.asStateFlow()

    protected val coroutineExceptionHandler : CoroutineExceptionHandler = CoroutineExceptionHandler { _ , exception : Throwable ->
        Log.e("BaseViewModel" , "Coroutine Exception: " , exception)
        handleError(exception = exception)
    }

    val _visibilityStates : MutableStateFlow<List<Boolean>> = MutableStateFlow(value = emptyList())
    val visibilityStates : StateFlow<List<Boolean>> = _visibilityStates.asStateFlow()

    private val _isFabVisible : MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isFabVisible : StateFlow<Boolean> = _isFabVisible.asStateFlow()

    private fun handleError(exception : Throwable) {
        viewModelScope.launch(context = coroutineExceptionHandler) {

            val errorType : ErrorType = when (exception) {
                is SecurityException -> ErrorType.SECURITY_EXCEPTION
                is IOException -> ErrorType.IO_EXCEPTION
                is ActivityNotFoundException -> ErrorType.ACTIVITY_NOT_FOUND
                is IllegalArgumentException -> ErrorType.ILLEGAL_ARGUMENT
                is android.database.sqlite.SQLiteException -> ErrorType.SQLITE_EXCEPTION
                else -> ErrorType.UNKNOWN_ERROR
            }

            handleError(errorType = errorType , ignoredException = exception)

            _uiErrorModel.value = UiErrorModel(
                showErrorDialog = true , errorMessage = when (errorType) {
                    ErrorType.SECURITY_EXCEPTION -> getApplication<Application>().getString(R.string.security_error)
                    ErrorType.IO_EXCEPTION -> getApplication<Application>().getString(R.string.io_error)
                    ErrorType.ACTIVITY_NOT_FOUND -> getApplication<Application>().getString(R.string.activity_not_found)
                    ErrorType.ILLEGAL_ARGUMENT -> getApplication<Application>().getString(R.string.illegal_argument_error)
                    ErrorType.SQLITE_EXCEPTION -> getApplication<Application>().getString(R.string.sqlite_error)
                    ErrorType.UNKNOWN_ERROR -> getApplication<Application>().getString(R.string.unknown_error)
                }
            )
        }
    }


    fun dismissErrorDialog() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _uiErrorModel.value = UiErrorModel(showErrorDialog = false)
        }
    }

    protected open fun handleError(errorType : ErrorType , ignoredException : Throwable) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            ErrorHandler.handleError(applicationContext = getApplication() , errorType = errorType)
        }
    }

    protected fun showLoading() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _isLoading.value = true
        }
    }

    protected fun hideLoading() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _isLoading.value = false
        }
    }

    protected fun showFab() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            _isFabVisible.value = true
        }
    }
}