package com.d4rk.androidtutorials.ui.android.clocks.clock.tabs
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentClockLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class ClockTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentClockLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentClockLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val textViewDigitalClockInputStream = resources.openRawResource(R.raw.text_clock_digital_xml)
        val textViewDigitalClock = textViewDigitalClockInputStream.readBytes().toString(Charsets.UTF_8)
        textViewDigitalClockInputStream.close()
        binding.textViewDigitalClockXml.text = textViewDigitalClock
        val textViewClockInputStream = resources.openRawResource(R.raw.text_clock_xml)
        val textViewClock = textViewClockInputStream.readBytes().toString(Charsets.UTF_8)
        textViewClockInputStream.close()
        binding.textViewTextClockXml.text = textViewClock
        val textViewAnalogClockInputStream = resources.openRawResource(R.raw.text_clock_analog_xml)
        val textViewAnalogClock = textViewAnalogClockInputStream.readBytes().toString(Charsets.UTF_8)
        textViewAnalogClockInputStream.close()
        binding.textViewAnalogClockXml.text = textViewAnalogClock
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
        binding.textViewDigitalClockXml.typeface = monospaceFont
        binding.textViewTextClockXml.typeface = monospaceFont
        binding.textViewAnalogClockXml.typeface = monospaceFont
    }
}