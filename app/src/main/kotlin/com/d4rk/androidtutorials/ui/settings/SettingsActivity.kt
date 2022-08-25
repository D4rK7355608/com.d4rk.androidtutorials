package com.d4rk.androidtutorials.ui.settings
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.SettingsActivityBinding
import com.google.android.material.textview.MaterialTextView
class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var binding: SettingsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.settings, SettingsFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        val darkModeString = getString(R.string.theme)
        key?.let {
            if (it == darkModeString) sharedPreferences?.let { pref ->
                val darkModeValues = resources.getStringArray(R.array.dark_mode_values)
                when (pref.getString(darkModeString, darkModeValues[0])) {
                    darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    darkModeValues[3] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.settings_preferences, rootKey)
            val moreApps: Preference? = findPreference("more_apps")
            if (moreApps != null) {
                moreApps.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle(R.string.more_apps)
                    val view: View = layoutInflater.inflate(R.layout.fragment_dialog, null)
                    val musicSleepTimerString: MaterialTextView = view.findViewById(R.id.musicSleepTimerString)
                    val englishWithLidiaString: MaterialTextView = view.findViewById(R.id.englishWithLidiaString)
                    val qrCodeScannerString: MaterialTextView = view.findViewById(R.id.qrCodeScannerString)
                    val lowBrightnessString: MaterialTextView = view.findViewById(R.id.lowBrightnessString)
                    alertDialog.setView(view)
                    alertDialog.create()
                    view.findViewById<View?>(R.id.musicSleepTimer)?.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.musicsleeptimer.plus"))
                        startActivity(intent)
                    }
                    musicSleepTimerString.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.musicsleeptimer.plus"))
                        startActivity(intent)
                    }
                    view.findViewById<View?>(R.id.englishWithLidia)?.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.englishwithlidia.plus"))
                        startActivity(intent)
                    }
                    englishWithLidiaString.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.englishwithlidia.plus"))
                        startActivity(intent)
                    }
                    view.findViewById<View?>(R.id.qrCodeScanner)?.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.qrcodescanner.plus"))
                        startActivity(intent)
                    }
                    qrCodeScannerString.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.qrcodescanner.plus"))
                        startActivity(intent)
                    }
                    view.findViewById<View?>(R.id.lowBrightness)?.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.lowbrightness"))
                        startActivity(intent)
                    }
                    lowBrightnessString.setOnClickListener {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.d4rk.lowbrightness"))
                        startActivity(intent)
                    }
                    alertDialog.setNegativeButton(R.string.cool, null)
                    alertDialog.show()
                    true
                }
            }
            val changelog: Preference? = findPreference("changelog")
            if (changelog != null) {
                changelog.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    val alertDialog: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    alertDialog.setTitle(R.string.changelog)
                    alertDialog.setMessage(R.string.changes)
                    alertDialog.setNegativeButton(R.string.cool, null)
                    alertDialog.show()
                    true
                }
            }
            val share: Preference? = findPreference("share")
            if (share != null) {
                share.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    val sharingIntent = Intent(Intent.ACTION_SEND)
                    sharingIntent.type = "text/plain"
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.d4rk.androidtutorials")
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject)
                    startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_using)))
                    true
                }
            }
        }
    }
}