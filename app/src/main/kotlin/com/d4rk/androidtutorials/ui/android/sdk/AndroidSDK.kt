package com.d4rk.androidtutorials.ui.android.sdk
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityAndroidSdkBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class AndroidSDK : AppCompatActivity() {
    private lateinit var binding : ActivityAndroidSdkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidSdkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        val adRequestBuilder = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestBuilder)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
    }
}