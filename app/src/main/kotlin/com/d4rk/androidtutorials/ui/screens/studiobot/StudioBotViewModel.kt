package com.d4rk.androidtutorials.ui.screens.studiobot

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.data.model.api.ApiMessageData
import com.d4rk.androidtutorials.ui.screens.studiobot.repository.StudioBotRepository
import com.d4rk.androidtutorials.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class StudioBotViewModel(application : Application) : BaseViewModel(application) {
    private val repository = StudioBotRepository()

    private val _chatHistory = MutableStateFlow(
        value = listOf(
            ApiMessageData(
                id = UUID.randomUUID() , text = "Welcome to Studio Bot! How can I help you today?" , isBot = true
            )
        )
    )
    val chatHistory : StateFlow<List<ApiMessageData>> = _chatHistory.asStateFlow()

    init {
        startChat()
    }

    private fun startChat() {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            showLoading()
            repository.createChatSessionRepository(modelName = "gemini-1.5-flash" , apiKey = BuildConfig.API_KEY , onChatCreated = { _ -> })
            hideLoading()
        }
    }

    fun sendMessage(message : String) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val newHistory = chatHistory.value.toMutableList()
            newHistory.add(
                element = ApiMessageData(
                    id = UUID.randomUUID() , text = message , isBot = false
                )
            )
            _chatHistory.value = newHistory.toList()

            repository.sendMessageRepository(message = message) { reply ->
                val updatedHistory = _chatHistory.value.toMutableList()
                updatedHistory.add(
                    element = ApiMessageData(
                        id = UUID.randomUUID() , text = reply , isBot = true
                    )
                )
                _chatHistory.value = updatedHistory.toList()
            }
        }
    }
}