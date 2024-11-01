package com.d4rk.androidtutorials.ui.components.lessons

import android.content.Context
import android.view.SoundEffectConstants
import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.d4rk.androidtutorials.constants.ui.lessons.LessonConstants
import com.d4rk.androidtutorials.data.model.ui.screens.UiLesson
import com.d4rk.androidtutorials.ui.components.animations.bounceClick
import com.d4rk.androidtutorials.ui.components.navigation.openLessonDetailActivity

@Composable
fun LessonItem(lesson : UiLesson , context : Context) {
    Card(
        modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
    ) {
        when (lesson.type) {
            LessonConstants.TYPE_FULL_IMAGE_BANNER -> {
                FullImageBannerLessonItem(lesson = lesson , context = context)
                Spacer(modifier = Modifier.width(8.dp))
            }

            LessonConstants.TYPE_SQUARE_IMAGE -> {
                SquareImageLessonItem(lesson = lesson , context = context)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

@Composable
fun FullImageBannerLessonItem(lesson : UiLesson , context : Context) {
    Card(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openLessonDetailActivity(context = context, lessonId = "lesson_${lesson.id}")
            }) {
        Column {
            AsyncImage(
                model = lesson.bannerImageUrl ,
                contentDescription = null ,
                modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(ratio = 16f / 9f) ,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp) ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TitleAndDescriptionColumn(title = lesson.title , description = lesson.description)
            }
            if (lesson.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                TagRow(lesson.tags)
            }
            Spacer(modifier = Modifier.height(12.dp))
            ButtonsRow()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Composable
fun SquareImageLessonItem(lesson : UiLesson , context : Context) {
    Card(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openLessonDetailActivity(context = context, lessonId = "lesson_${lesson.id}")
            }) {
        Column {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp) ,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = lesson.squareImageUrl ,
                    contentDescription = null ,
                    modifier = Modifier
                            .size(98.dp)
                            .aspectRatio(ratio = 1f / 1f)
                            .clip(RoundedCornerShape(12.dp)) ,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(
                    modifier = Modifier.weight(1f) , verticalArrangement = Arrangement.SpaceBetween
                ) {
                    TitleAndDescriptionColumn(
                        title = lesson.title , description = lesson.description
                    )
                }
            }
            if (lesson.tags.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                TagRow(lesson.tags)
            }
            Spacer(modifier = Modifier.height(12.dp))
            ButtonsRow()
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun TitleAndDescriptionColumn(title : String , description : String) {
    Column {
        if (title.isNotEmpty()) {
            Text(
                text = title ,
                style = MaterialTheme.typography.titleMedium ,
                maxLines = 1 ,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (description.isNotEmpty()) {
            Text(
                text = description ,
                style = MaterialTheme.typography.bodyMedium ,
                maxLines = 2 ,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
fun ButtonsRow() {
    val view : View = LocalView.current
    var isFavorite by remember { mutableStateOf(value = false) }

    Row(
        modifier = Modifier.fillMaxWidth() , horizontalArrangement = Arrangement.End
    ) {
        IconButton(modifier = Modifier.bounceClick() , onClick = {
            view.playSoundEffect(SoundEffectConstants.CLICK)
            // TODO: Share action
        }) {
            Icon(
                imageVector = Icons.Outlined.Share , contentDescription = null
            )
        }

        IconButton(modifier = Modifier.bounceClick() , onClick = {
            view.playSoundEffect(SoundEffectConstants.CLICK)
            isFavorite = ! isFavorite
        }) {
            Icon(
                imageVector = if (isFavorite) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder ,
                contentDescription = null ,
                tint = if (isFavorite) MaterialTheme.colorScheme.error else LocalContentColor.current
            )
        }
    }
}


@Composable
fun TagRow(tags : List<String>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth() ,
        contentPadding = PaddingValues(horizontal = 16.dp) ,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(tags) { tag ->
            AssistChip(
                onClick = { } ,
                label = { Text(text = tag) } ,
            )
        }
    }
}