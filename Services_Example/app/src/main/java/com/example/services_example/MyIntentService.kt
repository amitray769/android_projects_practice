package com.example.services_example

import android.app.IntentService
import android.content.Intent
import android.util.Log

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        // This method is called on a background thread when an Intent is received.

        if (intent != null) {
            val action = intent.action
            when (action) {
                ACTION_TASK1 -> {
                    performTask1()
                }
                ACTION_TASK2 -> {
                    performTask2()
                }
            }
        }
    }

    private fun performTask1() {
        // Perform your background task 1 here.
        Log.d(TAG, "Task 1 is running")
    }

    private fun performTask2() {
        // Perform your background task 2 here.
        Log.d(TAG, "Task 2 is running")
    }

    companion object {
        const val ACTION_TASK1 = "com.example.myapp.action.TASK1"
        const val ACTION_TASK2 = "com.example.myapp.action.TASK2"
        private const val TAG = "MyIntentService"
    }
}