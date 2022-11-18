package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.myapplication.downloader.DownloadStatus
import com.example.myapplication.downloader.FileDownloader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    // TODO:
    val fileUrls = arrayOf(
        "https://bn-temp.imgix.net/personalised_kit/portrait/5602fe68-358f-11ed-8f5c-af2160e1b131/Z1dzMGFQNEJDOEk9.pdf",
        "https://dummyimage.com/300/09f/fff.png",
        "https://dummyimage.com/900/09f/fff.png"
    )

    lateinit var file: FileDownloader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //todo add Varags in the constructor
        file = FileDownloader(this, fileUrls)
        val btn = findViewById<Button>(R.id.btn_download)
        btn.setOnClickListener {
            file.start()
            downloadFile()
        }
    }

    private fun downloadFile() {
        CoroutineScope(Dispatchers.IO).launch {
            file.downloadedFile.collect() { status ->
                getStatus(status)
            }
        }
    }

    private fun getStatus(status: DownloadStatus) {
        when (status) {
            is DownloadStatus.Failure -> {

            }
            is DownloadStatus.Success -> {
                //Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
            }
            is DownloadStatus.Loading -> {
                //Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_SHORT).show()

            }
            is DownloadStatus.Complete -> {

            }
        }
    }
}