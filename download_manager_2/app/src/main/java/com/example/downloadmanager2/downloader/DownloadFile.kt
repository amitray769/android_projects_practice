package com.example.downloadmanager2.downloader

interface DownloadFile {
    fun downloadFile(url : String) : Long
}