package com.istudio.services.modules.started_services.intent_service.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.istudio.services.utils.toast.toast

const val NAME_OF_OUR_SERVICE = "our intent service"
class IntentDownloadService : IntentService(NAME_OF_OUR_SERVICE) {

    companion object {
        const val SERVICE_DURATION = 4000L
        var isServiceRunning = false
    }


    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        displayToast("OnHandleIntent is execute on:---> {${Thread.currentThread().name}}")
        Thread.sleep(SERVICE_DURATION)
    }

    @Deprecated("Deprecated in Java")
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        isServiceRunning = true
        displayToast("Service is created")
    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        super.onDestroy()
        isServiceRunning = false
        displayToast("Service is destroyed")
    }


    private fun displayToast(message: String) {
        applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
    }

}