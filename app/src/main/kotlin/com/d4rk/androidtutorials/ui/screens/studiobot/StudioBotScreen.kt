package com.d4rk.androidtutorials.ui.screens.studiobot

import android.os.Bundle
import android.widget.Toast
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Android
import androidx.compose.material.icons.outlined.CopyAll
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.model.api.ApiMessageData
import com.d4rk.androidtutorials.ui.components.spacers.ButtonHorizontalSpacer
import com.d4rk.androidtutorials.ui.components.spacers.LargeHorizontalSpacer
import com.d4rk.androidtutorials.ui.components.layouts.LoadingScreen
import com.d4rk.androidtutorials.ui.components.modifiers.bounceClick
import com.d4rk.androidtutorials.utils.helpers.ClipboardHelper
import com.google.firebase.analytics.FirebaseAnalytics
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
        Column(
            modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
        ) {
            Box(modifier = Modifier.weight(weight = 1f)) {
                ChatHistory(messages = chatHistory.value)
            }
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 16.dp) ,
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(value = userInput ,
                                  singleLine = true ,
                                  onValueChange = { input ->
                                      userInput = input.replaceFirstChar { char ->
                                          if (char.isLowerCase()) char.titlecase() else char.toString()
                                      }
                                  } ,
                                  modifier = Modifier
                                          .fillMaxWidth()
                                          .weight(weight = 1f)
                                          .padding(end = 8.dp) ,
                                  shape = CircleShape ,
                                  placeholder = { Text(text = stringResource(id = R.string.type_a_message)) } ,
                                  keyboardOptions = KeyboardOptions(
                                      capitalization = KeyboardCapitalization.Sentences ,
                                      autoCorrectEnabled = true ,
                                      imeAction = ImeAction.Send
                                  ) ,
                                  keyboardActions = KeyboardActions(onSend = {
                                      viewModel.sendMessage(message = userInput)
                                      userInput = ""
                                  })
                )
                IconButton(enabled = userInput.isNotBlank() , onClick = {
                    if (userInput.isNotBlank()) {
                        viewModel.sendMessage(message = userInput)
                        userInput = ""
                    }
                } , modifier = Modifier
                        .size(size = 56.dp)
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
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = messages.size) {
        scrollState.animateScrollTo(value = scrollState.maxValue)
    }

    Column(
        modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
    ) {
        messages.forEach { message ->
            val isLatestBotMessage = message.isBot && message == messages.lastOrNull { it.isBot }
            MessageBubble(
                text = message.text ,
                isBot = message.isBot ,
                showTypingAnimation = isLatestBotMessage ,
                scrollState = scrollState
            )
        }
    }
}


@Composable
fun MessageBubble(
    text : String , isBot : Boolean , showTypingAnimation : Boolean , scrollState : ScrollState
) {
    var currentVisibleCharacters by remember(text) { mutableIntStateOf(value = if (showTypingAnimation) 0 else text.length) }
    val textToDisplay = remember(key1 = text , key2 = currentVisibleCharacters) {
        text.substring(
            0 , currentVisibleCharacters
        )
    }

    LaunchedEffect(key1 = text , key2 = showTypingAnimation) {
        when {
            showTypingAnimation -> {
                currentVisibleCharacters = 0
                while (currentVisibleCharacters < text.length) {
                    delay(timeMillis = 24)
                    currentVisibleCharacters ++
                }
                scrollState.animateScrollTo(scrollState.maxValue)
            }

            else -> {
                currentVisibleCharacters = text.length
            }
        }
    }

    Row(
        modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp) ,
        verticalAlignment = Alignment.Top ,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (isBot) {
            ProfilePicture(
                icon = Icons.Outlined.Android ,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
            LargeHorizontalSpacer()
        }

        Card(
            shape = RoundedCornerShape(16.dp) , modifier = Modifier.weight(weight = 1f)
        ) {
            Column {
                Text(
                    text = textToDisplay , modifier = Modifier.padding(all = 16.dp)
                )
                MessageActions(text = text , isBot = isBot)
            }
        }

        if (! isBot) {
            LargeHorizontalSpacer()
            ProfilePicture(
                icon = Icons.Outlined.PersonOutline ,
                backgroundColor = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@Composable
fun ProfilePicture(backgroundColor : Color , icon : ImageVector) {
    Box(
        modifier = Modifier
                .size(size = 48.dp)
                .background(
                    color = backgroundColor , shape = CircleShape
                ) , contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(size = 32.dp) ,
            imageVector = icon ,
            contentDescription = "Profile Picture Icon" ,
            tint = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun MessageActions(text : String , isBot : Boolean) {
    val context = LocalContext.current
    var isLiked by remember { mutableStateOf(value = false) }
    var isDisliked by remember { mutableStateOf(value = false) }
    val firebaseAnalytics = remember { FirebaseAnalytics.getInstance(context) }

    Row(
        modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp) ,
        horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row {
            if (isBot) {
                IconButton(
                    modifier = Modifier.bounceClick() ,
                    onClick = {
                        if (isLiked) {
                            isLiked = false
                        }
                        else {
                            isLiked = true
                            isDisliked = false
                        }

                        val params = Bundle().apply {
                            putString("message" , text)
                        }
                        firebaseAnalytics.logEvent(
                            if (isLiked) "message_liked" else "message_unliked" , params
                        )
                    } ,
                ) {
                    Icon(
                        modifier = Modifier.size(size = ButtonDefaults.IconSize) ,
                        imageVector = if (isLiked) Icons.Filled.ThumbUp else Icons.Outlined.ThumbUp ,
                        contentDescription = "Like Icon" ,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(
                    modifier = Modifier.bounceClick() ,
                    onClick = {
                        if (isDisliked) {
                            isDisliked = false
                        }
                        else {
                            isDisliked = true
                            isLiked = false
                        }
                        val params = Bundle().apply {
                            putString("message" , text)
                        }
                        firebaseAnalytics.logEvent(
                            if (isDisliked) "message_disliked" else "message_undisliked" , params
                        )
                    } ,
                ) {
                    Icon(
                        modifier = Modifier.size(size = ButtonDefaults.IconSize) ,
                        imageVector = if (isDisliked) Icons.Filled.ThumbDown else Icons.Outlined.ThumbDown ,
                        contentDescription = "Dislike Icon" ,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        TextButton(modifier = Modifier.bounceClick() , onClick = {
            ClipboardHelper.copyTextToClipboard(
                context = context ,
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
                modifier = Modifier.size(size = ButtonDefaults.IconSize)
            )
            ButtonHorizontalSpacer()
            Text(text = stringResource(id = android.R.string.copy))
        }
    }
}