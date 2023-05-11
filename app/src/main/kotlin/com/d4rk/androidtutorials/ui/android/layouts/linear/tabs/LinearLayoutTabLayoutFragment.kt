package com.d4rk.androidtutorials.ui.android.layouts.linear.tabs
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentLinearLayoutLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class LinearLayoutTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentLinearLayoutLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentLinearLayoutLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val textViewLinearLayoutVerticalInputStream = resources.openRawResource(R.raw.text_linear_layout_vertical_xml)
        val textViewLinearLayoutVertical = textViewLinearLayoutVerticalInputStream.readBytes().toString(Charsets.UTF_8)
        textViewLinearLayoutVerticalInputStream.close()
        binding.textViewVerticalXml.text = textViewLinearLayoutVertical
        val textViewLinearLayoutHorizontalInputStream = resources.openRawResource(R.raw.text_linear_layout_horizontal_xml)
        val textViewLinearLayoutHorizontal = textViewLinearLayoutHorizontalInputStream.readBytes().toString(Charsets.UTF_8)
        textViewLinearLayoutHorizontalInputStream.close()
        binding.textViewHorizontalXml.text = textViewLinearLayoutHorizontal
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val preference = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val monospaceFont: Typeface? = when (preference.getString(getString(R.string.key_monospace_font), "0")) {
            "0" -> ResourcesCompat.getFont(requireContext(), R.font.font_audiowide)
            "1" -> ResourcesCompat.getFont(requireContext(), R.font.font_fira_code)
            "2" -> ResourcesCompat.getFont(requireContext(), R.font.font_jetbrains_mono)
            "3" -> ResourcesCompat.getFont(requireContext(), R.font.font_noto_sans_mono)
            "4" -> ResourcesCompat.getFont(requireContext(), R.font.font_poppins)
            "5" -> ResourcesCompat.getFont(requireContext(), R.font.font_roboto_mono)
            else -> ResourcesCompat.getFont(requireContext(), R.font.font_audiowide)
        }
        binding.textViewVerticalXml.typeface = monospaceFont
        binding.textViewHorizontalXml.typeface = monospaceFont
    }
}