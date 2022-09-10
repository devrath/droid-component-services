package com.droid.sample.intent_service.services

import android.app.IntentService
import android.content.Intent
import android.util.Log
import android.widget.Toast


const val NAME_OF_OUR_SERVICE = "our intent service"
const val INTENT_COUNTER_KEY = "intent_counter_key"

class IntentDownloadService : IntentService(NAME_OF_OUR_SERVICE){

    companion object {
        const val SERVICE_DURATION = 4000L
    }

    var currentCounter : Int = 0

    @Deprecated("Deprecated in Java")
    override fun onHandleIntent(intent: Intent?) {
        displayToast("Service is started")
        intent?.let {
            currentCounter = it.getIntExtra(INTENT_COUNTER_KEY,0)
            // --> Long running operation
            try {
                Thread.sleep(SERVICE_DURATION)
                displayToast("Task is Completed->$currentCounter")
            } catch (exception: Exception) { exception.printStackTrace() }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        displayToast("Service is created")
    }

    @Deprecated("Deprecated in Java")
    override fun onDestroy() {
        super.onDestroy()
        displayToast("Service is destroyed")
    }


    private fun displayToast(message: String) {
        // applicationContext?.let { it -> toast(msg = message,isShort = false, context = it) }
        Log.d("IntentService",message)
    }

}