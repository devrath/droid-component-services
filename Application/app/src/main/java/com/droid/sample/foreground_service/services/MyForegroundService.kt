package com.droid.sample.foreground_service.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.os.Looper
import androidx.core.app.NotificationCompat
import com.droid.sample.R
import com.droid.sample.SelectionActivity
import com.droid.sample.foreground_service.ForegroundServiceActivity
import com.droid.sample.utils.extensions.toast.toast

class MyForegroundService : Service() {

    companion object {
        const val SERVICE_DURATION = 10000L

        const val NOTIFICATION_CHANNEL_NAME = "Synchronize service channel"
        const val NOTIFICATION_CHANNEL_ID = "Synchronize ID"
    }

    lateinit var pendingIntent : PendingIntent

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        displayToast("Service is started")
        // --> Long running operation
        showNotification()
        lonRunningThread?.start()

        return START_STICKY
    }

    override fun onDestroy() {
        if(lonRunningThread!=null && lonRunningThread?.isAlive == true){
            lonRunningThread?.interrupt()
        }
        displayToast("Service is destroyed")
        super.onDestroy()
    }

    private var lonRunningThread: Thread? = object : Thread() {
        override fun run() {
            Looper.prepare()
            while (true) {
                try {
                    sleep(SERVICE_DURATION)
                    displayToast("Task is Completed")
                    if (::pendingIntent.isInitialized) {
                        pendingIntent.cancel()
                    }
                }
                catch (exception: Exception) { exception.printStackTrace() }
            }
        }
    }

    private fun showNotification() {
        createNotificationChannel()

        val notificationIntent = Intent(this, SelectionActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
        val actionIntent =  setMutableFlag(context = applicationContext,intent = notificationIntent)

        val notification = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Title of foreground service")
            .setContentText("Content of foreground service")
            .setContentIntent(actionIntent)
            .build()

        startForeground(1, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }
    }

    private fun setMutableFlag(context: Context, intent: Intent, isBroadcast:Boolean=false): PendingIntent? {

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val immutableFlag = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
            if(isBroadcast){
                PendingIntent.getBroadcast(context, 0, intent, immutableFlag)
            }else{
                PendingIntent.getActivity(context, 0, intent, immutableFlag)
            }
        } else {
            val updateFlag = PendingIntent.FLAG_UPDATE_CURRENT
            if(isBroadcast){
                PendingIntent.getBroadcast(context, 0, intent, updateFlag)
            }else{
                PendingIntent.getActivity(context, 0, intent, updateFlag)
            }
        }
    }

    private fun displayToast(message: String) {
        applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
    }

}