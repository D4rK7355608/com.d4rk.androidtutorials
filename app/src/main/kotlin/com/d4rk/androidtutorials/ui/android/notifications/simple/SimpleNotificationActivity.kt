package com.d4rk.androidtutorials.ui.android.notifications.simple
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.databinding.ActivityNotificationBinding
class SimpleNotificationActivity : AppCompatActivity() {
    private val simpleChannelId = "simple_notification"
    private val simpleNotificationId = 1
    private lateinit var binding: ActivityNotificationBinding
    @Suppress("DEPRECATION")
    private val handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonShowNotification.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            }
            val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val simpleChannel = NotificationChannel(simpleChannelId, "Simple Notifications",NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(simpleChannel)
            val notificationBuilder = NotificationCompat.Builder(this, simpleChannelId)
                .setSmallIcon(R.drawable.ic_notification_important)
                .setContentTitle("Title")
                .setContentText("Look! You've added notifications to your app!")
                .setAutoCancel(true)
            notificationManager.notify(simpleNotificationId, notificationBuilder.build())
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, SimpleNotificationCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}