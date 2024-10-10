package com.example.services_example

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyBoundService : Service() {

    // Binder given to clients
    private val binder = LocalBinder()

    // Class used for the client Binder
    inner class LocalBinder : Binder() {
        fun getService(): MyBoundService = this@MyBoundService
    }

    // Return the communication channel to the service
    override fun onBind(intent: Intent?): IBinder? {
        return binder
    }

    // Example function that clients can call
    fun getData(): String {
        return "Hello from the Service!"
    }
}
