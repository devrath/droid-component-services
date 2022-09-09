package com.droid.sample.service_normal.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import com.droid.sample.utils.extensions.toast.toast


class NormalDownloadService : Service(){

    companion object {
        const val SERVICE_DURATION = 25000L

        private var instance: NormalDownloadService? = null

        fun stopService() {
            if(isCurrentServiceRunning()){ instance?.stopSelf() }
        }

        fun isCurrentServiceRunning(): Boolean { return instance != null }
    }

    init { instance = this }

    override fun onBind(p0: Intent?): IBinder? {
       return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        displayToast("Service is started")
        // --> Long running operation
        lonRunningThread?.start()

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        if(lonRunningThread!=null && lonRunningThread?.isAlive == true){
            lonRunningThread?.interrupt()
        }
        instance = null
        displayToast("Service is destroyed")
        super.onDestroy()
    }

    var lonRunningThread: Thread? = object : Thread() {
        override fun run() {
            Looper.prepare()
            while (true) {
                try { sleep(SERVICE_DURATION) }
                catch (exception: Exception) { exception.printStackTrace() }
            }
        }
    }

    private fun displayToast(message: String) {
        applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
    }
}