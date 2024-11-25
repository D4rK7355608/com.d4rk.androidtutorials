package com.d4rk.androidtutorials.ui.screens.help

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import com.d4rk.androidtutorials.utils.IntentUtils
import com.google.android.gms.tasks.Task
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HelpViewModel(application : Application) : BaseViewModel(application) {

    private var _reviewInfo : MutableState<ReviewInfo?> = mutableStateOf(value = null)
    val reviewInfo : State<ReviewInfo?> = _reviewInfo

    init {
        initializeVisibilityStates()
    }

    private fun initializeVisibilityStates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            delay(timeMillis = 100L)
            showFab()
        }
    }

    fun requestReviewFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            val reviewManager : ReviewManager = ReviewManagerFactory.create(getApplication())
            val request : Task<ReviewInfo> = reviewManager.requestReviewFlow()
            val packageName : String = getApplication<Application>().packageName
            request.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _reviewInfo.value = task.result
                }
                else {
                    task.exception?.let {
                        task.exception?.printStackTrace()
                        IntentUtils.sendEmailToDeveloper(getApplication())
                    }
                }
            }.addOnFailureListener {
                IntentUtils.openUrl(
                    getApplication() ,
                    url = "https://play.google.com/store/apps/details?id=$packageName&showAllReviews=true"
                )
            }
        }
    }

    fun launchReviewFlow(activity : HelpActivity , reviewInfo : ReviewInfo) {
        val reviewManager : ReviewManager = ReviewManagerFactory.create(activity)
        reviewManager.launchReviewFlow(activity , reviewInfo)
    }
}