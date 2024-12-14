package com.d4rk.androidtutorials.ui.components.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun NoLessonsScreen(
    text : String = "No lessons found." ,
    icon : ImageVector = Icons.Default.Info ,
    iconDescription : String = "No lessons icon"
) {
    Box(
        modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon ,
                contentDescription = iconDescription ,
                modifier = Modifier
                        .size(58.dp)
                        .padding(bottom = 16.dp) ,
                tint = MaterialTheme.colorScheme.primary
            )
            Text(
                text = text ,
                style = MaterialTheme.typography.displaySmall.copy(textAlign = TextAlign.Center) ,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}