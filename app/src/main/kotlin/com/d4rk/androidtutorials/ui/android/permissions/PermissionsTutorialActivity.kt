package com.d4rk.androidtutorials.ui.android.permissions
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityPermissionsTutorialBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class PermissionsTutorialActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPermissionsTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPermissionsTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        val adRequestBuilder = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestBuilder)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.buttonMore.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/guide/topics/permissions/overview")))
        }
    }
}