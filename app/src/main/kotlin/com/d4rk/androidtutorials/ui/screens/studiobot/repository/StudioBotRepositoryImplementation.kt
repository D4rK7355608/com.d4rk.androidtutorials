package com.d4rk.androidtutorials.ui.screens.studiobot.repository

import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.GenerateContentResponse
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig

abstract class StudioBotRepositoryImplementation {
    private lateinit var chat : Chat

    fun createChatSessionImplementation(modelName : String , apiKey : String) : Chat {
        val generationConfig = generationConfig {
            temperature = 1f
            topK = 40
            topP = 0.95f
            maxOutputTokens = 8192
            responseMimeType = "text/plain"
        }

        val model = GenerativeModel(
            modelName = modelName ,
            apiKey = apiKey ,
            generationConfig = generationConfig ,
            systemInstruction = content { text(text = "You are Studio Bot, an AI assistant integrated into the Android Studio Tutorials app developed by D4rK. Your primary function is to assist users with Android development by providing clear explanations, code examples, and troubleshooting advice. Maintain a professional and instructional tone in all interactions. If a user query falls outside the scope of Android development, politely inform them of your limitations") } ,
        )
        val createdChat = Chat(model = model)
        chat = createdChat
        return createdChat
    }

    suspend fun sendMessageToChatImplementation(message : String) : GenerateContentResponse {
        return chat.sendMessage(prompt = message.trim())
    }
}