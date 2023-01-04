package com.d4rk.androidtutorials.ui.android.alerts.alertdialog.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentAlertDialogCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import me.zhanghai.android.fastscroll.FastScrollerBuilder
class AlertDialogTabCodeFragment : Fragment() {
    private lateinit var binding: FragmentAlertDialogCodeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentAlertDialogCodeBinding.inflate(inflater, container, false)
        FastScrollerBuilder(binding.scrollView).useMd2Style().build()
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        val inputStream = resources.openRawResource(R.raw.text_alertdialog_kotlin)
        val xmlText = inputStream.bufferedReader().use { it.readText() }
        inputStream.close()
        binding.textView.text = xmlText
        return binding.root
    }
}