package com.d4rk.androidtutorials.ui.android.clocks.clock.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
}