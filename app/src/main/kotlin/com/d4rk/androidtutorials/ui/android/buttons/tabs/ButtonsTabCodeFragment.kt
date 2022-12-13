package com.d4rk.androidtutorials.ui.android.buttons.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentButtonsCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
class ButtonsTabCodeFragment : Fragment() {
    private lateinit var _binding: FragmentButtonsCodeBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentButtonsCodeBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        val adRequestBuilder = AdRequest.Builder().build()
        binding.adView.loadAd(adRequestBuilder)
        val inputStream: InputStream = resources.openRawResource(R.raw.text_buttons_kotlin)
        val byteArrayOutputStream = ByteArrayOutputStream()
        var i: Int
        try {
            i = inputStream.read()
            while (i != - 1) {
                byteArrayOutputStream.write(i)
                i = inputStream.read()
            }
            inputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        binding.textViewButtonsCode.text = byteArrayOutputStream.toString()
        return binding.root
    }
}