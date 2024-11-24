package com.d4rk.androidtutorials.ui.screens.studiobot.repository

import android.app.Application
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse

abstract class StudioBotRepositoryImplementation(val application : Application) {
    private lateinit var chat : Chat

    fun createChatSessionImplementation(modelName : String , apiKey : String) : Chat {
        val generationConfig = com.google.ai.client.generativeai.type.generationConfig {
            temperature = 1f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        }

        val model = GenerativeModel(modelName , apiKey , generationConfig)
        val createdChat = Chat(model)
        chat = createdChat
        return createdChat
    }

    suspend fun sendMessageToChatImplementation(message : String) : GenerateContentResponse {
        return chat.sendMessage(message.trim())
    }
}
