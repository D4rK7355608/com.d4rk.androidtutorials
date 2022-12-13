package com.d4rk.androidtutorials.helpers
import android.content.SharedPreferences
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.d4rk.androidtutorials.MainActivity
import com.d4rk.androidtutorials.ui.startup.StartupActivity
class HelperActivity : AppCompatActivity() {
    private lateinit var prefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        prefs = getSharedPreferences("startup", MODE_PRIVATE)
    }
    override fun onResume() {
        super.onResume()
        if (prefs.getBoolean("value", true)) {
            prefs.edit().putBoolean("value", false).apply()
            startActivity(Intent(this, StartupActivity::class.java))
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}