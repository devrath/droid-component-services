package com.istudio.services.module_selection

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.istudio.services.ui.composables.AppButton
import com.istudio.services.ui.composables.AppText

@Composable
fun ModuleSelectionScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        Spacer(modifier = Modifier.height(16.dp))
        AppText(text = "UnBound Services")
        Spacer(modifier = Modifier.height(3.dp))

        AppButton(text = "Normal Service", onClick = {
            navController.navigate(ModuleDemo.NormalServiceScreen.rout)
        })

        AppButton(text = "Intent Service", onClick = {
            navController.navigate(ModuleDemo.IntentServiceScreen.rout)
        })

        AppButton(text = "Job Scheduler", onClick = {
            navController.navigate(ModuleDemo.JobSchedularScreen.rout)
        })

        AppButton(text = "Job Intent Service", onClick = {
            navController.navigate(ModuleDemo.JobIntentServiceScreen.rout)
        })

        Spacer(modifier = Modifier.height(16.dp))
        AppText(text = "Bound Services")
        Spacer(modifier = Modifier.height(3.dp))

        AppButton(text = "Media Player", onClick = {
            navController.navigate(ModuleDemo.MediaPlayerServiceScreen.rout)
        })

    }
}