package com.d4rk.androidtutorials.ui.screens.help.repository

import android.app.Application
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiHelpQuestion
import com.d4rk.androidtutorials.ui.screens.help.HelpActivity
import com.google.android.play.core.review.ReviewInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HelpRepository(
    dataStore : DataStore , application : Application
) : HelpRepositoryImplementation(application , dataStore) {

    suspend fun getFAQsRepository(onSuccess : (ArrayList<UiHelpQuestion>) -> Unit) {
        withContext(Dispatchers.IO) {
            val questions = getFAQsImplementation().map { (questionRes , summaryRes) ->
                UiHelpQuestion(
                    question = application.getString(questionRes) ,
                    answer = application.getString(summaryRes)
                )
            }.toCollection(ArrayList())

            withContext(Dispatchers.Main) {
                onSuccess(questions)
            }
        }
    }

    suspend fun requestReviewFlowRepository(
        onSuccess : (ReviewInfo) -> Unit , onFailure : () -> Unit
    ) {
        withContext(Dispatchers.IO) {
            requestReviewFlowImplementation(onSuccess , onFailure)
        }
    }

    suspend fun launchReviewFlowRepository(activity : HelpActivity , reviewInfo : ReviewInfo) {
        withContext(Dispatchers.IO) {
            launchReviewFlowImplementation(activity , reviewInfo)
        }
    }
}
