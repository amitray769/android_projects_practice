package com.example.myapplication.downloader

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.lang.ref.WeakReference

class FileDownloader(
    context: Context,
    private val fileUrls: Array<String>,
) {
    private val context  = WeakReference(context)
    private var downloadManager: DownloadManager? = null
    private val downloadIds = mutableListOf<Long>()
    private val downloadedFilePaths = mutableListOf<String>()

    var downloadedFile: Flow<DownloadStatus> = flow { emit(DownloadStatus.Loading(true, 0.1f)) }


    private val onComplete = object : BroadcastReceiver() {

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE == action) {
                downloadedFile = flow {

                    try {
                        val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0)
                        val query = DownloadManager.Query()
                        query.setFilterById(downloadId)
                        val cursor = downloadManager?.query(query)
                        if (cursor?.moveToFirst() == true) {
                            val columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                            if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                                val uriString: String =
                                    cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                                val uri = Uri.parse(uriString).toString()
                                downloadIds.remove(downloadId)
                                downloadedFilePaths.add(uri)
                                emit(DownloadStatus.Success(uri))


                            }
                        }
                    } catch (e: Exception) {
                        emit(DownloadStatus.Failure(e.message))
                    }


                    //if the downloadIds is empty means, all the downloads are completed
                    if (downloadIds.isEmpty()) {
                        emit(DownloadStatus.Complete(downloadedFilePaths.size))
                        unRegisterBroadcastReceiver()
                    }
                }
            }
        }
    }

    fun getFilePath() = downloadedFile


    fun start() {
        for (url in fileUrls) {
            initializeDownload(context, url)

        }
    }


    init {
        downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        registerBroadcastReceiver()
    }

    private fun initializeDownload(context: Context, url: String) {

        val fileNameWithExtension = url.substring(url.lastIndexOf("/") + 1, url.length)
        val fileExtension = url.substring(url.lastIndexOf("."), url.length)

        val type = checkType(fileExtension)
        type?.let { filetype ->
            val filepathAndExt = createFile(context, fileNameWithExtension)
            startDownload(
                context,
                url,
                fileNameWithExtension,
                Environment.DIRECTORY_DOWNLOADS,
                filepathAndExt
            )
        } ?: run {
            downloadedFile = flow { emit(DownloadStatus.Failure("File type not found")) }
        }

    }


    private fun checkType(fileExtension: String): String? {
        return when (fileExtension) {
            ".jpg", ".png" -> {
                TYPE_IMAGE
            }
            ".pdf" -> {
                TYPE_PDF
            }
            ".mp4" -> {
                TYPE_VIDEO
            }
            else -> {
                null
            }
        }
    }

    private fun createFile(context: Context, fileNameWithExtension: String): String {
        val subPath = "/BrokerNetwork/$fileNameWithExtension"
        val filepath = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
            .toString() + subPath

        if (!File(filepath).exists()) {
            File(filepath).parentFile?.mkdirs()
            File(filepath).createNewFile()
        }
        return subPath
    }


    private fun startDownload(
        context: Context,
        link: String,
        fileNameWithExtension: String,
        directory: String,
        path: String
    ) {

        val request = DownloadManager.Request(Uri.parse(link))
        request.setTitle(fileNameWithExtension)
            .setDescription("File is downloading...")
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            .setDestinationInExternalFilesDir(context, directory, path)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        downloadIds.add(downloadManager!!.enqueue(request))
    }

    private fun registerBroadcastReceiver() {
        context.registerReceiver(
            onComplete,
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        )
    }

    private fun unRegisterBroadcastReceiver() {
        context.unregisterReceiver(onComplete)
    }

    companion object {
        const val TYPE_IMAGE = "image"
        const val TYPE_VIDEO = "video"
        const val TYPE_PDF = "video"
    }
}

