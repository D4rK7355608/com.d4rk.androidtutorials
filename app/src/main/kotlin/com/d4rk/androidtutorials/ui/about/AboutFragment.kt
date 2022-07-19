package com.d4rk.androidtutorials.ui.about
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.FragmentAboutBinding
import com.d4rk.androidtutorials.ui.settings.SettingsActivity
import com.d4rk.androidtutorials.ui.viewmodel.ViewModel
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
class AboutFragment : Fragment(R.layout.fragment_about) {
    private lateinit var _binding: FragmentAboutBinding
    private val binding get() = _binding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        ViewModelProvider(this)[ViewModel::class.java]
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val content = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        binding.itemSettingsMoreAboutVersion.text = content
        binding.itemSettingsMoreAboutSettings.setOnClickListener {
            val intent = Intent (activity, SettingsActivity::class.java)
            activity?.startActivity(intent)
        }
        binding.itemSettingsMoreAboutIcon.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/d4rk7355608"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutGoogleDev.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developers.google.com/profile/u/D4rK7355608"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutYoutube.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/D4rK7355608"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/D4rK7355608/com.d4rk.androidtutorials"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutTwitter.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/D4rK7355608"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutXda.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://forum.xda-developers.com/m/d4rk7355608.10095012"))
            startActivity(intent)
        }
        binding.itemSettingsMoreAboutLibraries.setOnClickListener {
            OssLicensesMenuActivity.setActivityTitle(getString(R.string.libraries))
            val intent = Intent(activity, OssLicensesMenuActivity::class.java)
            startActivity(intent)
        }
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}