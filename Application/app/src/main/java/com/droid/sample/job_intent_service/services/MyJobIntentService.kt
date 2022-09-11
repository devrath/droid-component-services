package com.droid.sample.job_intent_service.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService
import com.droid.sample.intent_service.services.INTENT_COUNTER_KEY
import com.droid.sample.intent_service.services.IntentDownloadService

const val SERVICE_NAME = "Job Intent Service"

class MyJobIntentService : JobIntentService() {

    companion object {
        private const val JOB_ID = 1012
        const val SERVICE_DURATION = 4000L
        fun startWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }
    }

    var currentCounter : Int = 0

    override fun onHandleWork(intent: Intent) {
        logMessage("Service is created")

        intent?.let {
            if(it.hasExtra(INTENT_COUNTER_KEY)){
                currentCounter = it.getIntExtra(INTENT_COUNTER_KEY,0)
                // --> Long running operation
                try {
                    Thread.sleep(SERVICE_DURATION)
                    logMessage("Task is Completed->$currentCounter")
                } catch (exception: Exception) { exception.printStackTrace() }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        logMessage("Service is destroyed")
    }

    private fun logMessage(message: String) {
        Log.d("JobIntentService",message)
    }

}