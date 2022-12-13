package com.d4rk.androidtutorials.ui.android.shortcuts
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceFragmentCompat
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityShortcutsBinding
class ShortcutsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShortcutsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShortcutsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_shortcuts, SettingsFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.buttonMore.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com/studio/intro/keyboard-shortcuts")))
        }
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_shortcuts, rootKey)
        }
    }
}