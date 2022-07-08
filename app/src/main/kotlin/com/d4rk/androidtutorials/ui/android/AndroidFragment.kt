package com.d4rk.androidtutorials.ui.android
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.d4rk.androidtutorials.R
class AndroidFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.android_studio, rootKey)
    }
}