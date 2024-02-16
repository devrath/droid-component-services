package com.istudio.services.modules.intent_service

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.istudio.services.modules.intent_service.services.IntentDownloadService
import com.istudio.services.modules.normal_service.services.NormalDownloadService
import com.istudio.services.ui.composables.AppButton
import com.istudio.services.utils.toast.toast

@Composable
fun IntentServiceScreen(navController: NavHostController) {

    val cxt = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AppButton(text = "Start", onClick = {
            val intent = Intent(cxt, IntentDownloadService::class.java)
            cxt.startService(intent)
        })

    }
}