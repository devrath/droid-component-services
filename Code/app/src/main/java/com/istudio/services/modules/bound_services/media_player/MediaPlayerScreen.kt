package com.istudio.services.modules.bound_services.media_player

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
import com.istudio.services.modules.started_services.intent_service.services.IntentDownloadService
import com.istudio.services.ui.composables.AppButton

@Composable
fun MediaPlayerScreen(navController: NavHostController) {

    val cxt = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AppButton(text = "Play", onClick = {
            val intent = Intent(cxt, IntentDownloadService::class.java)
            cxt.startService(intent)
        })
        AppButton(text = "Stop", onClick = {
            val intent = Intent(cxt, IntentDownloadService::class.java)
            cxt.startService(intent)
        })

    }
}