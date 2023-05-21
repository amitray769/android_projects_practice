package com.example.downloadmanager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.downloadmanager2.downloader.AndroidDownloader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloadButton = findViewById<Button>(R.id.btn_download)
        val fileUrl = "https://unsplash.com/photos/s1puI2BWQzQ/download?ixid=M3wxMjA3fDB8MXxhbGx8fHx8fHx8fHwxNjg0NjgxNDQ1fA&force=true&w=640"
        downloadButton.setOnClickListener{
            startYourDownload(fileUrl)
        }
    }

    private fun startYourDownload(url : String){
       val androidDownloader=  AndroidDownloader(this)
        androidDownloader.downloadFile(url)
    }

}