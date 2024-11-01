package com.d4rk.androidtutorials.ui.screens.studiobot.repository

import android.app.Application
import com.google.ai.client.generativeai.Chat
import com.google.ai.client.generativeai.type.asTextOrNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StudioBotRepository(application : Application) :
    StudioBotRepositoryImplementation(application) {

    suspend fun createChatSessionRepository(
        modelName : String ,
        apiKey : String ,
        onChatCreated : (Chat) -> Unit ,
    ) {
        withContext(Dispatchers.IO) {
            val create = createChatSessionImplementation(modelName = modelName , apiKey = apiKey)
            withContext(Dispatchers.Main) {
                onChatCreated(create)
            }
        }
    }

    suspend fun sendMessageRepository(message : String , onMessageSent : (String) -> Unit) {
        withContext(Dispatchers.IO) {
            val response = sendMessageToChatImplementation(message)
            val messageContent =
                    response.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.asTextOrNull()
                        ?: ""
            withContext(Dispatchers.Main) {
                onMessageSent(messageContent)
            }
        }
    }
}
