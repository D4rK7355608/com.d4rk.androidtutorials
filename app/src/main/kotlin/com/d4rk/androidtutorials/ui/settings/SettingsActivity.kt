package com.d4rk.androidtutorials.ui.settings
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.BuildConfig
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivitySettingsBinding
import com.d4rk.androidtutorials.ui.dialogs.RequireRestartDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textview.MaterialTextView
class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.settings, SettingsFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this)
    }
    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, rootKey: String?) {
        val themeValues = resources.getStringArray(R.array.preference_theme_values)
        when (rootKey) {
            getString(R.string.key_theme) -> sharedPreferences?.let { pref ->
                when (pref.getString(getString(R.string.key_theme), themeValues[0])) {
                    themeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                    themeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    themeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    themeValues[3] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
                }
            }
        }
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_settings, rootKey)
            val labelVisibilityMode = findPreference<ListPreference>(getString(R.string.key_bottom_navigation_bar_labels))
            labelVisibilityMode?.setOnPreferenceChangeListener { _, _ ->
                val restartDialog = RequireRestartDialog()
                restartDialog.show(childFragmentManager, RequireRestartDialog::class.java.name)
                true
            }
            val moreApps: Preference? = findPreference(getString(R.string.key_more_apps))
            if (moreApps != null) {
                moreApps.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    val view: View = layoutInflater.inflate(R.layout.fragment_dialog, null)
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(R.string.more_apps)
                        .setIcon(R.drawable.ic_shop)
                        .setNegativeButton(android.R.string.cancel, null)
                        .setView(view)
                        .create()
                        .show()
                    val textViewMusicSleepTimer: MaterialTextView = view.findViewById(R.id.text_view_music_sleep_timer)
                    val textViewEnglishWithLidia: MaterialTextView = view.findViewById(R.id.text_view_english_with_lidia)
                    val textViewQRCodeScanner: MaterialTextView = view.findViewById(R.id.text_view_qr_code_scanner)
                    val textViewLowBrightness: MaterialTextView = view.findViewById(R.id.text_view_low_brightness)
                    val textViewCleaner: MaterialTextView = view.findViewById(R.id.text_view_cleaner)
                    val urls = mapOf(textViewCleaner to "https://play.google.com/store/apps/details?id=com.d4rk.cleaner.plus", textViewMusicSleepTimer to "https://play.google.com/store/apps/details?id=com.d4rk.musicsleeptimer.plus", textViewEnglishWithLidia to "https://play.google.com/store/apps/details?id=com.d4rk.englishwithlidia.plus", textViewQRCodeScanner to "https://play.google.com/store/apps/details?id=com.d4rk.qrcodescanner.plus", textViewLowBrightness to "https://play.google.com/store/apps/details?id=com.d4rk.lowbrightness")
                    urls.forEach { (view, url) -> view.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        }
                    }
                    true
                }
            }
            val changelogPreference: Preference? = findPreference(getString(R.string.key_changelog))
            if (changelogPreference != null) {
                changelogPreference.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle(requireContext().getString(R.string.changelog_title, BuildConfig.VERSION_NAME))
                        .setIcon(R.drawable.ic_changelog)
                        .setMessage(R.string.changes)
                        .setNegativeButton(android.R.string.cancel, null)
                        .show()
                    true
                }
            }
            val sharePreference: Preference? = findPreference(getString(R.string.key_share))
            sharePreference?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                val sharingIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.d4rk.androidtutorials")
                    putExtra(Intent.EXTRA_SUBJECT, R.string.share_subject)
                }
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_using)))
                true
            }
        }
    }
}