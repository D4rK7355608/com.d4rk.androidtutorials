package com.d4rk.androidtutorials.ui.screens.studiobot

import android.widget.Toast
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.data.api.ApiMessageData
import com.d4rk.androidtutorials.ui.components.animations.bounceClick
import com.d4rk.androidtutorials.ui.screens.loading.LoadingScreen
import com.d4rk.androidtutorials.utils.ClipboardUtil
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
                    singleLine = true ,
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
fun ChatHistory(messages : List<ApiMessageData>) {
    Column(
        modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
    ) {
        messages.forEach { message ->
            val isLatestBotMessage = message.isBot && message == messages.lastOrNull { it.isBot }

            MessageBubble(
                text = message.text ,
                isBot = message.isBot ,
                showTypingAnimation = isLatestBotMessage
            )

        }
    }
}

@Composable
fun MessageBubble(text : String , isBot : Boolean , showTypingAnimation : Boolean) {
    var visibleCharacters by remember(text) { mutableIntStateOf(if (showTypingAnimation) 0 else text.length) }
    val textToDisplay = remember(text , visibleCharacters) { text.substring(0 , visibleCharacters) }

    val context = LocalContext.current

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
                .padding(16.dp) ,
        contentAlignment = if (isBot) Alignment.CenterStart else Alignment.CenterEnd
    ) {
        Card(
            shape = RoundedCornerShape(16.dp)
        ) {

            Column {
                Text(
                    text = textToDisplay , modifier = Modifier.padding(16.dp)
                )
                Row(
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp) ,
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(modifier = Modifier.bounceClick() , onClick = {
                        ClipboardUtil.copyTextToClipboard(context = context ,
                                                          label = "Message" ,
                                                          text = text ,
                                                          onShowSnackbar = {
                                                              Toast.makeText(
                                                                  context ,
                                                                  "Message copied to clipboard" ,
                                                                  Toast.LENGTH_SHORT
                                                              ).show()
                                                          })
                    } , contentPadding = PaddingValues(horizontal = 8.dp)) {
                        Icon(
                            imageVector = Icons.Outlined.CopyAll ,
                            contentDescription = "Copy Message" ,
                            modifier = Modifier.size(ButtonDefaults.IconSize)
                        )
                        Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                        Text(text = stringResource(id = android.R.string.copy))
                    }
                }
            }
        }
    }
}