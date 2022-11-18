package com.example.myapplication.downloader

sealed class DownloadStatus{
    data class Loading(var value : Boolean,var progress : Float) : DownloadStatus()
    data class Success(val file : DownloadResponse) : DownloadStatus()
    data class Failure(val errorMsg: String?) : DownloadStatus()
    data class Complete(val numberOfFiles: Int) : DownloadStatus()
}


class DownloadResponse()
