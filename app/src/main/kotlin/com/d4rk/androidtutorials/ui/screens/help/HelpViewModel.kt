package com.d4rk.androidtutorials.ui.screens.help

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiHelpScreen
import com.d4rk.androidtutorials.ui.screens.help.repository.HelpRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import com.d4rk.androidtutorials.utils.IntentUtils
import com.google.android.play.core.review.ReviewInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HelpViewModel(application : Application) : BaseViewModel(application) {
    private val repository = HelpRepository(DataStore(application) , application)

    private val _uiState = MutableStateFlow(UiHelpScreen())
    val uiState : StateFlow<UiHelpScreen> = _uiState

    init {
        initializeVisibilityStates()
        getFAQs()
        requestReviewFlow()
    }

    private fun initializeVisibilityStates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            delay(timeMillis = 100L)
            showFab()
        }
    }

    private fun getFAQs() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.getFAQsRepository { faqList ->
                _uiState.value = _uiState.value.copy(questions = faqList)
            }
        }
    }

    private fun requestReviewFlow() {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.requestReviewFlowRepository(onSuccess = { reviewInfo ->
                _uiState.value = _uiState.value.copy(reviewInfo = reviewInfo)
            } , onFailure = {
                IntentUtils.sendEmailToDeveloper(getApplication())
            })
        }
    }

    fun launchReviewFlow(activity : HelpActivity , reviewInfo : ReviewInfo) {
        viewModelScope.launch(coroutineExceptionHandler) {
            repository.launchReviewFlowRepository(activity , reviewInfo)
        }
    }
}