package com.d4rk.androidtutorials.data.model.ui.animations.defaults

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin

data class Transition(
    val scale : ScaleTransition? = null ,
    val fade : FadeTransition? = null ,
    val slide : SlideTransition? = null
)

data class ScaleTransition(
    val from : Offset = Offset(1f , 1f) ,
    val to : Offset = Offset(1f , 1f) ,
    val animationSpec : AnimationSpec<Float> = tween() ,
    val transformOrigin : TransformOrigin = TransformOrigin.Center
)

data class FadeTransition(
    val from : Float = 1f , val to : Float = 1f , val animationSpec : AnimationSpec<Float> = tween()
)

data class SlideTransition(
    val from : Offset = Offset(0f , 0f) ,
    val to : Offset = Offset(0f , 0f) ,
    val animationSpec : AnimationSpec<Offset> = tween()
)
