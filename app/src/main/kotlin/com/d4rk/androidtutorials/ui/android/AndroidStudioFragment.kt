package com.d4rk.androidtutorials.ui.android
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.d4rk.androidtutorials.R
class AndroidStudioFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_android_studio, rootKey)
    }
}