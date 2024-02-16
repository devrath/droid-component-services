package com.istudio.services.modules.job_scheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavHostController
import com.istudio.services.modules.job_scheduler.services.MyJobScheduler
import com.istudio.services.ui.composables.AppButton
import com.istudio.services.utils.toast.toast


const val TAG = "Job scheduler"

@Composable
fun JobSchedularScreen(navController: NavHostController) {

    val cxt = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        val cxt = LocalContext.current

        AppButton(text = "Start", onClick = {

            val jobScheduler = cxt.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?
            val componentName = ComponentName(cxt, MyJobScheduler::class.java)
            val jobId = 111

            val jobInfo = JobInfo.Builder(jobId, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY) // Set network requirements if needed
                .setRequiresCharging(false) // Set charging requirements if needed
                .setPeriodic((1000 * 60 * 15).toLong()) // Set the interval in milliseconds (e.g., 15 minutes)
                .build()

            val resultCode = jobScheduler?.schedule(jobInfo)

            if (resultCode == JobScheduler.RESULT_SUCCESS) {
                Log.d(TAG, "Job scheduled successfully")
                toast(msg = "Job scheduled successfully",isShort = false, context = cxt)
            } else {
                Log.e(TAG, "Job scheduling failed")
                toast(msg = "Job scheduling failed",isShort = false, context = cxt)
            }

        })

    }
}