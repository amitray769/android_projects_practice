package com.example.calleriddisplay.view

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.calleriddisplay.worker.IconChangeWorker
import com.example.calleriddisplay.databinding.ActivityMainBinding
import com.example.calleriddisplay.utils.toggleIcon
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var toggle = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        checkRequiredPermissions()
        initView()
    }

    private fun initView() {
        val workRequest = PeriodicWorkRequestBuilder<IconChangeWorker>(45, TimeUnit.MINUTES)

        //Triggering the work manager to change the launcher icon at every hour automatically
        WorkManager.getInstance(application).enqueue(workRequest.build())

        //Changing the Launcher icon  manually on click of the button
        binding.button.setOnClickListener {
            toggle = toggleIcon(this, toggle)
            Toast.makeText(this, "Launcher Icon Changed", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Checking required permissions for READ_PHONE_STATE and ACTION_MANAGE_OVERLAY_PERMISSION
     */
    private fun checkRequiredPermissions() {
        val permissionsRequest =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                if (!it) {
                    Toast.makeText(this, "Need permissions", Toast.LENGTH_LONG).show()
                }
            }

        permissionsRequest.launch(Manifest.permission.READ_PHONE_STATE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
            startActivity(
                Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${packageName}")
                )
            )
        }
    }


}