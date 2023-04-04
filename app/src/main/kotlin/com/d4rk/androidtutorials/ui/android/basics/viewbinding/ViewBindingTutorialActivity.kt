package com.d4rk.androidtutorials.ui.android.basics.viewbinding
import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityViewBindingTutorialBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.InputStream
class ViewBindingTutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewBindingTutorialBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(this)
        binding.adView.loadAd(AdRequest.Builder().build())
        binding.moreAboutViewBindingButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/topic/libraries/view-binding")))
        }
        val resources = resources
        val bindingGradle = resources.openRawResource(R.raw.text_binding_gradle)
        binding.bindingText.text = readTextFromInputStream(bindingGradle)
        val bindingActivity = resources.openRawResource(R.raw.text_binding_activity)
        binding.bindingActivitiesText.text = readTextFromInputStream(bindingActivity)
        val bindingFragment = resources.openRawResource(R.raw.text_binding_fragment)
        binding.bindingFragmentsText.text = readTextFromInputStream(bindingFragment)
    }
    private fun readTextFromInputStream(inputStream: InputStream): String {
        return inputStream.use {
            it.bufferedReader().use { reader ->
                reader.readText()
            }
        }
    }
    override fun onResume() {
        super.onResume()
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val preferenceFont = preference.getBoolean(getString(R.string.key_monospace_font), false)
        if (preferenceFont) {
            val monospaceFont: Typeface? = ResourcesCompat.getFont(this, R.font.font_roboto_mono)
            binding.bindingText.typeface = monospaceFont
            binding.bindingActivitiesText.typeface = monospaceFont
            binding.bindingFragmentsText.typeface = monospaceFont
        }
    }
}