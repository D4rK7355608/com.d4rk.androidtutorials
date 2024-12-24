package com.d4rk.androidtutorials.ui.screens.lessons.repository

import com.d4rk.androidtutorials.data.model.ui.screens.UiLessonScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Repository class for handling home screen data and operations.
 *
 * @param dataStore The DataStore instance for accessing user preferences.
 * @param application The application instance for accessing resources and external files directory.
 * @author Mihai-Cristian Condrea
 */
class LessonRepository : LessonRepositoryImplementation() {

    suspend fun getLessonRepository(
        lessonId : String , onSuccess : (UiLessonScreen?) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val lesson = getLessonImplementation(lessonId = lessonId)
            withContext(Dispatchers.Main) {
                onSuccess(lesson)
            }
        }
    }
}