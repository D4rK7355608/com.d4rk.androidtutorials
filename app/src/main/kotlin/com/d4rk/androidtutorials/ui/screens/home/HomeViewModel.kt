package com.d4rk.androidtutorials.ui.screens.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.data.datastore.DataStore
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.screens.home.repository.HomeRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application : Application) : BaseViewModel(application) {
    private val repository = HomeRepository(DataStore(application) , application)
    private val _lessons = MutableStateFlow<List<UiLesson>>(emptyList())
    val lessons : StateFlow<List<UiLesson>> = _lessons.asStateFlow()

    init {
        getHomeLessons()
    }

    private fun getHomeLessons() {
        viewModelScope.launch(coroutineExceptionHandler) {
            showLoading()
            repository.getHomeLessonsRepository { fetchedLessons ->
                _lessons.value = fetchedLessons
            }
            hideLoading()
        }
    }
}