package com.d4rk.androidtutorials.ui.android.notifications.inbox
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
class InboxNotificationActivity : AppCompatActivity() {
    private val notificationChannelId = "inbox_notification"
    private val notificationId = 1
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
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val channel = NotificationChannel(notificationChannelId, "Inbox Notifications", NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
            val notificationBuilder = NotificationCompat.Builder(this, notificationChannelId)
                .setSmallIcon(R.drawable.ic_notification_important)
                .setContentTitle("5 new messages")
                .setStyle(NotificationCompat.InboxStyle().addLine("Message 1").addLine("Message 2").setSummaryText("+3 more"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
            notificationManager.notify(notificationId, notificationBuilder.build())
        }
        binding.floatingButtonShowSyntax.setOnClickListener {
            startActivity(Intent(this, InboxNotificationCodeActivity::class.java))
        }
        handler.postDelayed({
            binding.floatingButtonShowSyntax.shrink()
        }, 5000)
    }
}