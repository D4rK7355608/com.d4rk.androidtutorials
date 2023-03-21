package com.d4rk.androidtutorials
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.NotificationCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import com.d4rk.androidtutorials.databinding.ActivityMainBinding
import com.d4rk.androidtutorials.ui.settings.SettingsActivity
import com.d4rk.androidtutorials.ui.startup.StartupActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationBarView
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var prefs: SharedPreferences
    private lateinit var appUpdateManager: AppUpdateManager
    private val requestUpdateCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        appUpdateManager = AppUpdateManagerFactory.create(this)
        prefs = getSharedPreferences("startup", MODE_PRIVATE)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val themeKey = getString(R.string.key_theme)
        val labelKey = getString(R.string.key_bottom_navigation_bar_labels)
        val themeValues = resources.getStringArray(R.array.preference_theme_values)
        val themeDefaultValue = getString(R.string.default_value_theme)
        val nightMode = when (sharedPreferences.getString(themeKey, themeDefaultValue)) {
            themeValues[0] -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            themeValues[1] -> AppCompatDelegate.MODE_NIGHT_NO
            themeValues[2] -> AppCompatDelegate.MODE_NIGHT_YES
            themeValues[3] -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
        val bottomNavigationBarLabelsValues = resources.getStringArray(R.array.preference_bottom_navigation_bar_labels_values)
        val labelDefaultValue = getString(R.string.default_value_bottom_navigation_bar_labels)
        binding.navView.labelVisibilityMode = when (sharedPreferences.getString(labelKey, labelDefaultValue)) {
            bottomNavigationBarLabelsValues[0] -> NavigationBarView.LABEL_VISIBILITY_LABELED
            bottomNavigationBarLabelsValues[1] -> NavigationBarView.LABEL_VISIBILITY_SELECTED
            bottomNavigationBarLabelsValues[2] -> NavigationBarView.LABEL_VISIBILITY_UNLABELED
            else -> NavigationBarView.LABEL_VISIBILITY_AUTO
        }
        val navController by lazy {
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
            navHostFragment.navController
        }
        binding.navView.setupWithNavController(navController)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_home, R.id.navigation_android_studio, R.id.navigation_about))
        setupActionBarWithNavController(navController, appBarConfiguration)
        val prefs = getSharedPreferences("app_usage", MODE_PRIVATE)
        val lastUsedTimestamp = prefs.getLong("last_used", 0)
        val currentTimestamp = System.currentTimeMillis()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (currentTimestamp - lastUsedTimestamp > 3 * 24 * 60 * 60 * 1000) {
            val channelId = "app_usage_channel"
            val channel = NotificationChannel(channelId, "App Usage Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
            val builder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(getString(R.string.notification_last_time_used_title))
                .setContentText(getString(R.string.summary_notification_last_time_used))
                .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
        prefs.edit().putLong("last_used", currentTimestamp).apply()
        val appUpdateInfoTask = AppUpdateManagerFactory.create(this).appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                val updateChannelId = "update_channel"
                val updateChannel = NotificationChannel(updateChannelId, "Update Notifications", NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(updateChannel)
                val updateBuilder = NotificationCompat.Builder(this, updateChannelId)
                    .setSmallIcon(R.drawable.ic_notification_update)
                    .setContentTitle(getString(R.string.notification_update_title))
                    .setContentText(getString(R.string.summary_notification_update))
                    .setAutoCancel(true)
                    .setContentIntent(PendingIntent.getActivity(this, 0, Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName")), PendingIntent.FLAG_IMMUTABLE))
                notificationManager.notify(0, updateBuilder.build())
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this)
            .setTitle(R.string.close)
            .setMessage(R.string.summary_close)
            .setPositiveButton(android.R.string.yes) { _, _ ->
                super.onBackPressed()
                moveTaskToBack(true)
            }
            .setNegativeButton(android.R.string.no, null)
            .apply { show() }
    }
    override fun onResume() {
        super.onResume()
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val preferenceFirebase = prefs.getBoolean(getString(R.string.key_firebase), true)
        FirebaseAnalytics.getInstance(this).setAnalyticsCollectionEnabled(preferenceFirebase)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(preferenceFirebase)
        if (prefs.getBoolean("value", true)) {
            prefs.edit().putBoolean("value", false).apply()
            startActivity(Intent(this, StartupActivity::class.java))
        }
        appUpdateManager.appUpdateInfo.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.FLEXIBLE, this, requestUpdateCode)
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        @Suppress("DEPRECATION")
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == requestUpdateCode) {
            when (resultCode) {
                RESULT_OK -> {
                }
                RESULT_CANCELED -> {
                }
                ActivityResult.RESULT_IN_APP_UPDATE_FAILED -> {
                }
            }
        }
    }
}