package com.d4rk.androidtutorials.utils

import android.os.Build.VERSION.SDK_INT
import androidx.compose.ui.platform.LocalContext


/*
object ImageUtils {
    fun createGifImageLoader() = ImageLoader.Builder(LocalContext.current)
            .components {
                if (SDK_INT >= 28) {
                    add(ImageDecoderDecoder.Factory()) // For Android P and above
                } else {
                    add(GifDecoder.Factory()) // For Android O and below
                }
            }
            .build()
}*/
