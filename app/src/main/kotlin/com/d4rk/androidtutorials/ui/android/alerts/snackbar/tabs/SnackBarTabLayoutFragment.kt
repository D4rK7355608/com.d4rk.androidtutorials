package com.d4rk.androidtutorials.ui.android.alerts.snackbar.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentSnackBarLayoutBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.InputStream
class SnackBarTabLayoutFragment : Fragment() {
    private lateinit var binding: FragmentSnackBarLayoutBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSnackBarLayoutBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val inputStream: InputStream = resources.openRawResource(R.raw.text_center_button_xml)
        val xmlText = inputStream.readBytes().toString(Charsets.UTF_8)
        binding.textView.text = xmlText
        return binding.root
    }
}