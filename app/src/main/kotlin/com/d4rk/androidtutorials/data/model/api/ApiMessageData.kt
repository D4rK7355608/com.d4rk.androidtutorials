package com.d4rk.androidtutorials.data.model.api

import java.util.UUID

data class ApiMessageData(
    val id : UUID ,
    val text : String ,
    val isBot : Boolean ,
    val firstTimeMessage : Boolean = false ,
)