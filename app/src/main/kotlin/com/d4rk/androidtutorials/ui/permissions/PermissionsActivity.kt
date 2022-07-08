package com.d4rk.androidtutorials.ui.permissions
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.PermissionsActivityBinding
import com.kieronquinn.monetcompat.app.MonetCompatActivity
class PermissionsActivity : MonetCompatActivity() {
    private lateinit var binding: PermissionsActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PermissionsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().replace(R.id.permissions, SettingsFragment()).commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.permissions_preferences, rootKey)
        }
    }
}