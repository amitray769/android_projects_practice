package com.example.services_example

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.media.MediaPlayer
import android.media.audiofx.BassBoost
import android.net.Uri
import android.os.IBinder
import android.util.Log
import java.io.File
import java.io.InputStream
import java.util.concurrent.Executor


/**
* This is example Service
 *
 * @param test this is test param
 *
*/

class ExampleService() : Service() {
    private lateinit var player : MediaPlayer

    override fun onStartCommand(init : Intent , flag : Int , startId: Int):Int{
        player = MediaPlayer.create(this,R.raw.p5_text)
        player.isLooping = true
        player.start()
        val currentThread = Thread.currentThread()
        Log.d("Service_Example", "Thread Name: ${currentThread.name}")
       // Log.d("Service_Example","${currentThread}")
        return  START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
       // player.stop()
    }


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

}