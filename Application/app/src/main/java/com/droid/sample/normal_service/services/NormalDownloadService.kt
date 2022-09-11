package com.droid.sample.normal_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
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
        interuptTask()
        displayToast("Service is destroyed")
        super.onDestroy()
    }

    private fun interuptTask() {
        if (lonRunningThread != null && lonRunningThread?.isAlive == true) {
            lonRunningThread?.let {
                it.interrupt()
            }
        }
    }

    private var lonRunningThread: Thread? = object : Thread() {
        override fun run() {
            while (!currentThread().isInterrupted) {
                try {
                    if (Looper.myLooper()==null){ Looper.prepare(); }
                    sleep(SERVICE_DURATION)
                    displayToast("Task is Completed")
                    interuptTask()
                } catch (e: InterruptedException) {
                    currentThread().interrupt() // propagate interrupt
                }
            }
        }
    }

    private fun displayToast(message: String) {
        applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
    }

}