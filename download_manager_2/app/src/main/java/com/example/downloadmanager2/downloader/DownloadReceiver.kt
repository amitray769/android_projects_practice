package com.example.downloadmanager2.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

private const val ACTION_DOWNLOAD_COMPLETE = "ACTION_DOWNLOAD_COMPLETE"

class DownloadReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == ACTION_DOWNLOAD_COMPLETE){
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
            if (id != -1L){
                println("Download Completed")
            }
        }
    }
}