package com.istudio.services.modules.job_intent_service.services

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

const val TAG = "Job intent service"

class MyJobIntentService : JobIntentService() {

    companion object {
        const val SERVICE_DURATION = 4000L
        private const val JOB_ID = 1012
        fun startWork(context: Context, intent: Intent) {
            enqueueWork(context, MyJobIntentService::class.java, JOB_ID, intent)
        }
    }


    override fun onHandleWork(intent: Intent) {
        Thread.sleep(SERVICE_DURATION)
    }


    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "On Create is triggered")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On Destroy is triggered")
    }

}