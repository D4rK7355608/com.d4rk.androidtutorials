package com.d4rk.androidtutorials.ui.screens.studiobot

import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.data.api.ApiMessageData
import com.d4rk.androidtutorials.ui.components.animations.bounceClick
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen
import kotlinx.coroutines.delay

@Composable
fun StudioBotScreen() {
    val viewModel : StudioBotViewModel = viewModel()
    val isLoading : Boolean by viewModel.isLoading.collectAsState()

    val transition : Transition<Boolean> =
            updateTransition(targetState = ! isLoading , label = "LoadingTransition")
    val progressAlpha : Float by transition.animateFloat(label = "Progress Alpha") {
        if (it) 0f else 1f
    }

    var userInput by remember { mutableStateOf(value = "") }
    val chatHistory = viewModel.chatHistory.collectAsState()

    if (isLoading) {
        LoadingScreen(progressAlpha = progressAlpha)
    }
    else {
        Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                ChatHistory(messages = chatHistory.value)
            }
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp) ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = userInput ,
                    onValueChange = { userInput = it } ,
                    modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(end = 8.dp) ,
                    shape = CircleShape ,
                    placeholder = { Text(text = "Type a message...") } ,
                )

                IconButton(onClick = {
                    if (userInput.isNotBlank()) {
                        viewModel.sendMessage(userInput)
                        userInput = ""
                    }
                } , modifier = Modifier
                        .size(56.dp)
                        .bounceClick()) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.Send ,
                        contentDescription = "Send" ,
                    )
                }
            }
        }
    }
}

@Composable
fun ChatHistory(messages: List<ApiMessageData>) {
    Column(
        modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
    ) {
        messages.forEachIndexed { index, message ->
            val prevMessage = messages.reversed().getOrNull(index + 1)
            val isLatestBotMessage = message.isBot && message == messages.lastOrNull { it.isBot }

            MessageBubble(
                text = message.text,
                isBot = message.isBot,
                showTypingAnimation = isLatestBotMessage
            )

        }
    }
}

@Composable
fun MessageBubble(text : String , isBot : Boolean , showTypingAnimation : Boolean) {
    var visibleCharacters by remember(text) { mutableIntStateOf(if (showTypingAnimation) 0 else text.length) }
    val textToDisplay = remember(text , visibleCharacters) { text.substring(0 , visibleCharacters) }

    LaunchedEffect(key1 = text , key2 = showTypingAnimation) {
        when {
            showTypingAnimation -> {
                visibleCharacters = 0
                while (visibleCharacters < text.length) {
                    delay(timeMillis = 24)
                    visibleCharacters ++
                }
            }

            else -> {
                visibleCharacters = text.length
            }
        }
    }

    Box(
        modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp) ,
        contentAlignment = if (isBot) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Text(
                text = textToDisplay , modifier = Modifier.padding(16.dp)
            )
        }
    }
}