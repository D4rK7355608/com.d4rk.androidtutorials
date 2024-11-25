package com.d4rk.androidtutorials.ui.screens.lessons

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.d4rk.androidtutorials.ui.screens.settings.display.theme.style.AppTheme

class LessonActivity : AppCompatActivity() {
    private val viewModel : LessonViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val lessonId = intent?.data?.lastPathSegment
            println("Android -> lesson id got is: $lessonId")
            lessonId?.let {
                viewModel.getLesson(it)
            }
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize() , color = MaterialTheme.colorScheme.background
                ) {
                    val lesson = viewModel.lesson.collectAsState()
                    lesson.value?.let {
                        LessonScreen(
                            lesson = it , activity = this@LessonActivity , viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}