package com.d4rk.androidtutorials.ui.android.views.images.tabs
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.d4rk.androidtutorials.databinding.FragmentNoCodeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
class ImagesTabCodeFragment : Fragment() {
    private lateinit var binding: FragmentNoCodeBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentNoCodeBinding.inflate(inflater, container, false)
        MobileAds.initialize(requireContext())
        binding.adView.loadAd(AdRequest.Builder().build())
        return binding.root
    }
}