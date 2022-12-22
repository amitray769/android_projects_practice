package com.example.calleriddisplay.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import com.example.calleriddisplay.services.CallerIdService

private const val TAG = "IncomingCallReceiver"

/**
 * Broadcast Receiver to receive the incoming call broadcast and starting the service
 *
 */
class IncomingCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "Incoming call")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            return
        }

        if (intent == null || intent.action != TelephonyManager.ACTION_PHONE_STATE_CHANGED) {
            return
        }

        try {
            when (intent.getStringExtra(TelephonyManager.EXTRA_STATE)) {
                TelephonyManager.EXTRA_STATE_RINGING -> {
                    val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER)
                    val i = Intent(context, CallerIdService::class.java)
                    i.putExtra("phone_number", incomingNumber)

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context?.startForegroundService(i)
                    } else {
                        context?.startService(i)
                    }
                }

                TelephonyManager.EXTRA_STATE_IDLE -> {
                    context?.stopService(Intent(context, CallerIdService::class.java))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}