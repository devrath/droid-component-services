package com.istudio.services.modules.started_services.job_intent_service

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.istudio.services.modules.started_services.job_intent_service.services.MyJobIntentService
import com.istudio.services.ui.composables.AppButton

@Composable
fun JobIntentServiceScreen(navController: NavHostController) {

    val cxt = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AppButton(text = "Start", onClick = {
            MyJobIntentService.startWork(cxt, Intent())
        })

    }

}