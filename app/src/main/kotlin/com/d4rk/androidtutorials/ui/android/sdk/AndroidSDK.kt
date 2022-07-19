package com.d4rk.androidtutorials.ui.android.sdk
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityAndroidSdkBinding
class AndroidSDK : AppCompatActivity() {
    private lateinit var binding : ActivityAndroidSdkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidSdkBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}