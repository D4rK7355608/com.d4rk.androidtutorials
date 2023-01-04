package com.d4rk.androidtutorials.ui.android.basics.shortcuts.tabs
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityShortcutsCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class CodeShortcutsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShortcutsCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutsCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        binding.adView.loadAd(AdRequest.Builder().build())
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
    }
}