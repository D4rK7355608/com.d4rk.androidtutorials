package com.d4rk.androidtutorials.utils.extensions

import com.d4rk.androidtutorials.data.database.table.FavoriteLessonTable
import com.d4rk.androidtutorials.data.model.ui.screens.UiHomeLesson

fun UiHomeLesson.toFavoriteLessonTable() : FavoriteLessonTable = FavoriteLessonTable(
    lessonId = lessonId ,
    lessonTitle = lessonTitle ,
    lessonDescription = lessonDescription ,
    lessonType = lessonType ,
    thumbnailImageUrl = thumbnailImageUrl ,
    squareImageUrl = squareImageUrl ,
    deepLinkPath = deepLinkPath ,
    lessonTags = lessonTags ,
    isFavorite = isFavorite
)