package com.istudio.services.modules.started_services.job_scheduler.services

import android.app.job.JobParameters
import android.app.job.JobService
import android.util.Log

const val TAG = "Job scheduler"
class MyJobScheduler : JobService() {
    companion object {
        const val SERVICE_DURATION = 4000L
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "On Create is triggered")
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Thread.sleep(SERVICE_DURATION)
        // Remember to call jobFinished() when the task is complete
        jobFinished(params, false);
        return true; // Return true if there is still work to be done in the background
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        // Called when the job is stopped before completion
        // Return true to reschedule the job
        return false;
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "On Destroy is triggered")
    }
}