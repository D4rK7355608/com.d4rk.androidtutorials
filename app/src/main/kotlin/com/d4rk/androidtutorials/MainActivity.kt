package com.d4rk.androidtutorials
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.databinding.ActivityMainBinding
import com.d4rk.androidtutorials.ui.settings.SettingsActivity
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navController by lazy {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navHostFragment.navController
        }
        binding.navView.setupWithNavController(navController)
        val darkModeValues = resources.getStringArray(R.array.dark_mode_values)
        when (PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.theme), getString(R.string.theme_def_value))) {
            darkModeValues[0] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            darkModeValues[1] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            darkModeValues[2] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            darkModeValues[3] -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
        }
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_android_studio, R.id.navigation_about))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.settings-> {
            startActivity(Intent (this, SettingsActivity::class.java))
            true
        }  else -> {
            super.onOptionsItemSelected(item)
        }
    }
    @Deprecated("Deprecated in Java", ReplaceWith("moveTaskToBack(true)"))
    override fun onBackPressed() {
        moveTaskToBack(true)
    }
}