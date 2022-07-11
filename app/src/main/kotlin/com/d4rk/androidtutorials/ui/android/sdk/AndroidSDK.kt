package com.d4rk.androidtutorials.ui.android.sdk
import android.os.Bundle
import com.d4rk.androidtutorials.databinding.ActivityAndroidSdkBinding
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class AndroidSDK : MonetCompatActivity() {
    private lateinit var binding : ActivityAndroidSdkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidSdkBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}