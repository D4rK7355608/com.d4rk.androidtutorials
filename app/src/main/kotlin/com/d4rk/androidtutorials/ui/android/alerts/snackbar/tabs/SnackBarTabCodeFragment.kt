package com.d4rk.androidtutorials.ui.android.alerts.snackbar.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentSnackBarCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class SnackBarTabCodeFragment : Fragment() {
    private lateinit var binding: FragmentSnackBarCodeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentSnackBarCodeBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val inputStream = resources.openRawResource(R.raw.text_snack_bar_kotlin)
        val xmlText = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        binding.textView.text = xmlText
        return binding.root
    }
}