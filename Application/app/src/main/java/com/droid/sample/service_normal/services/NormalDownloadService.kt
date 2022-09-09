package com.droid.sample.service_normal.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import com.droid.sample.utils.extensions.activity.isMyServiceRunning
import com.droid.sample.utils.extensions.toast.toast


class NormalDownloadService : Service(){

    companion object {
        const val SERVICE_DURATION = 8000L
    }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        displayToast("Service is started")
        // --> Long running operation
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

    var lonRunningThread: Thread? = object : Thread() {
        override fun run() {
            Looper.prepare()
            while (true) {
                try {
                    sleep(SERVICE_DURATION)
                    displayToast("Task is Completed")
                }
                catch (exception: Exception) { exception.printStackTrace() }
            }
        }
    }

    private fun displayToast(message: String) {
        applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
    }

}