package com.droid.sample.foreground_service

import android.Manifest
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import com.droid.sample.databinding.ActivityServiceNormalBinding
import com.droid.sample.foreground_service.services.MyForegroundService
import com.droid.sample.normal_service.services.NormalDownloadService
import com.droid.sample.utils.extensions.activity.isMyServiceRunning
import com.droid.sample.utils.extensions.toast.toast
import com.google.android.material.snackbar.Snackbar

class ForegroundServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceNormalBinding
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceNormalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        checkNotificationChannelPermission()

        binding.apply {
            val manager = NotificationManagerCompat.from(this@ForegroundServiceActivity)

            startServiceId.setOnClickListener {
                if (manager.areNotificationsEnabled()){
                    val intent = Intent(this@ForegroundServiceActivity, MyForegroundService::class.java)
                    startService(intent)
                }
            }

            checkServiceId.setOnClickListener {
                if (isMyServiceRunning(MyForegroundService::class.java)) {
                    toast(
                        msg = "Service is running",
                        isShort = true,
                        context = this@ForegroundServiceActivity
                    )
                } else {
                    toast(
                        msg = "Service is not running",
                        isShort = true,
                        context = this@ForegroundServiceActivity
                    )
                }
            }

            stopServiceId.setOnClickListener {
                if (isMyServiceRunning(MyForegroundService::class.java)) {
                    val intent =
                        Intent(this@ForegroundServiceActivity, MyForegroundService::class.java)
                    stopService(intent)
                }
            }

        }
    }

    private fun checkNotificationChannelPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Display runtime permission flow !!
            val manager = NotificationManagerCompat.from(this)
            if (!manager.areNotificationsEnabled()){
                requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                    if (!isGranted) {
                        val snackbar = Snackbar
                            .make(binding.root, "Notification Settings", Snackbar.LENGTH_INDEFINITE)
                            .setAction("YES") {
                                showNotificationSettings(this)
                            }
                        snackbar.show()
                    }
                }
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private fun showNotificationSettings(context: Context, channelId: String? = null) {
        // MORE-INFO: https://stackoverflow.com/a/69452006/1083093

        val notificationSettingsIntent = when {

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O /*26*/ -> Intent().apply {
                action = when (channelId) {
                    null -> Settings.ACTION_APP_NOTIFICATION_SETTINGS
                    else -> Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS
                }
                channelId?.let { putExtra(Settings.EXTRA_CHANNEL_ID, it) }
                putExtra(Settings.EXTRA_APP_PACKAGE, context.packageName)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P /*28*/) {
                    flags += Intent.FLAG_ACTIVITY_NEW_TASK
                }
            }

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP /*21*/ -> Intent().apply {
                action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
                putExtra("app_package", context.packageName)
                putExtra("app_uid", context.applicationInfo.uid)
            }

            Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT /*19*/ -> Intent().apply {
                action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                addCategory(Intent.CATEGORY_DEFAULT)
                data = Uri.parse("package:${context.packageName}")
            }

            else -> null
        }
        notificationSettingsIntent?.let(context::startActivity)
    }
}