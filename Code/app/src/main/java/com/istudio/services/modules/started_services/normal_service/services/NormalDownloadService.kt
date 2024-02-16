package com.istudio.services.modules.started_services.normal_service.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import com.istudio.services.utils.toast.toast

class NormalDownloadService : Service() {

    companion object {
        const val SERVICE_DURATION = 8000L // 8-Seconds
        var isServiceRunning = false
    }


    override fun onBind(intent: Intent?): IBinder? {
        // Since it is not a binding service, We return null
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        initOnStartCommand()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        initOnDestroy()
        super.onDestroy()
    }


    /**
     * <*********************> INIT functions <*********************>
     */
    private fun initOnStartCommand() {
        // Set the flag that service is running
        isServiceRunning = true
        displayToast("Service is started")
        // <--> Long running operation <-->
        lonRunningThread?.start()
    }

    private fun initOnDestroy() {
        interuptTask()
        displayToast("Service is destroyed")
        // Set the flag that service has stopped
        isServiceRunning = false
    }
    /**
     * <*********************> INIT functions <*********************>
     */

    /**
     * <*********************> User defined functions <*********************>
     */
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
    /**
     * <*********************> User defined functions <*********************>
     */

}