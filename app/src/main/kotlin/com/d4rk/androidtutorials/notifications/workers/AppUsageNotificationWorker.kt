package com.d4rk.androidtutorials.notifications.workers

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.d4rk.androidtutorials.R
import com.d4rk.androidtutorials.data.datastore.DataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

/**
 * Worker class responsible for app usage notifications.
 *
 * This worker class extends the WorkManager's Worker class to perform background tasks for
 * app usage notifications. It checks the last app usage timestamp stored in preferences
 * and triggers a notification if the threshold for notification has been exceeded.
 *
 * @property context The application context used for accessing system services and resources.
 * @property workerParams The parameters for this worker instance.
 */
class AppUsageNotificationWorker(context : Context , workerParams : WorkerParameters) :
    Worker(context , workerParams) {
    private val dataStore = DataStore.getInstance(context = context)
    private val appUsageChannelId = "app_usage_channel"
    private val appUsageNotificationId = 0

    /**
     * Performs the background work for app usage notification checks.
     *
     * This function checks the last app usage timestamp stored in preferences and compares
     * it against the current timestamp. If the elapsed time exceeds a predefined notification
     * threshold (3 days), it triggers a notification to remind the user about app usage.
     *
     * @return The result of the worker operation, indicating success or failure.
     */
    @RequiresApi(Build.VERSION_CODES.O)
    override fun doWork() : Result {
        val currentTimestamp : Long = System.currentTimeMillis()
        val notificationThreshold : Int = 3 * 24 * 60 * 60 * 1000
        val lastUsedTimestamp : Long = runBlocking { dataStore.lastUsed.first() }
        if (currentTimestamp - lastUsedTimestamp > notificationThreshold) {
            val notificationManager : NotificationManager =
                    applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val appUsageChannel = NotificationChannel(
                appUsageChannelId ,
                applicationContext.getString(R.string.app_usage_notifications) ,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(appUsageChannel)
            val notificationBuilder : NotificationCompat.Builder =
                    NotificationCompat.Builder(applicationContext , appUsageChannelId)
                            .setSmallIcon(R.drawable.ic_notification_important)
                            .setContentTitle(applicationContext.getString(R.string.notification_last_time_used_title))
                            .setContentText(applicationContext.getString(R.string.summary_notification_last_time_used))
                            .setAutoCancel(true)
            notificationManager.notify(appUsageNotificationId , notificationBuilder.build())
        }
        runBlocking { dataStore.saveLastUsed(timestamp = currentTimestamp) }
        return Result.success()
    }
}