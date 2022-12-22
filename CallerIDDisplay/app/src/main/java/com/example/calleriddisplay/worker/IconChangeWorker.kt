package com.example.calleriddisplay.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.calleriddisplay.R
import com.example.calleriddisplay.utils.toggleIcon

/**
 * The worker for every 45 minutes at periodic intervals
 */
class IconChangeWorker(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    private var toggle = true
    override fun doWork(): Result {
        createNotification("Caller ID", "Launcher Icon Change Notification")
        return try {
            toggle = toggleIcon(context, toggle)
            Result.success()
        } catch (throwable: Throwable) {
            Log.e("", "Error Running toggle")
            Result.failure()
        }
    }


    private fun createNotification(title: String, description: String) {

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel =
                NotificationChannel("101", "channel", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_launcher2_foreground)

        notificationManager.notify(2, notificationBuilder.build())

    }
}