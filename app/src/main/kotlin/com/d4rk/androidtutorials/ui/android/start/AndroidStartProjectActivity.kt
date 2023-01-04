package com.d4rk.androidtutorials.ui.android.start
import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.d4rk.androidtutorials.databinding.ActivityAndroidStartProjectBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class AndroidStartProjectActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAndroidStartProjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidStartProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MobileAds.initialize(this)
        binding.adView.loadAd(AdRequest.Builder().build())
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        binding.textViewThirdStepSummary.movementMethod = LinkMovementMethod.getInstance()
    }
}