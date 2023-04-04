package com.d4rk.androidtutorials.ui.android.alerts.snackbar.tabs
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class SnackBarTabCodeFragment : Fragment() {
    private lateinit var binding: FragmentCodeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val inputStream = resources.openRawResource(R.raw.text_snack_bar_kotlin)
        val xmlText = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        binding.textView.text = xmlText
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val preference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val preferenceFont = preference.getBoolean(getString(R.string.key_monospace_font), false)
        if (preferenceFont) {
            val monospaceFont: Typeface? =
                ResourcesCompat.getFont(requireContext(), R.font.font_roboto_mono)
            binding.textView.typeface = monospaceFont
        }
    }
}