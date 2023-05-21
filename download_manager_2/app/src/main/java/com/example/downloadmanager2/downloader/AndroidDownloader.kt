package com.example.downloadmanager2.downloader

import android.app.DownloadManager
import android.content.Context
import android.webkit.MimeTypeMap
import androidx.core.net.toUri
import java.io.File

class AndroidDownloader(context: Context) : DownloadFile {

    private val downloadManager : DownloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String): Long {
        val fileName = getFileNameFromUri(url)
        val request = DownloadManager.Request(url.toUri())
            .setMimeType(parseMimeType(url))
            .setTitle("$fileName is downloading")
            .setDescription("Please click to open the list of downloads")
            .setAllowedOverMetered(true)
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        return downloadManager.enqueue(request)
    }


    private fun parseMimeType(url : String) : String{
        val file = File(url)
        val map = MimeTypeMap.getSingleton()
        val ext = MimeTypeMap.getFileExtensionFromUrl(file.name)
        val type = map.getMimeTypeFromExtension(ext)
        return type ?: "*/*"
    }

    private fun getFileNameFromUri(uri : String) : String {
        return uri.substring(uri.lastIndexOf( '/')+1,uri.lastIndex)
    }
}