package com.istudio.services.modules.bound_services.media_player

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import com.istudio.services.main.MainViewModel
import com.istudio.services.main.states.MainViewEvent
import com.istudio.services.modules.bound_services.media_player.services.PlayerService
import com.istudio.services.modules.started_services.intent_service.services.IntentDownloadService
import com.istudio.services.ui.composables.AppButton

@Composable
fun MediaPlayerScreen(
    navController: NavHostController
) {

    val cxt = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current


    // View model reference
    val viewModel: MainViewModel = hiltViewModel()
    // View state reference from view model
    val state = viewModel.viewState


    var isServiceBound by remember { mutableStateOf(false) }


    // If `lifecycleOwner` changes, dispose and reset the effect
    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for sending analytics events
        val observer = LifecycleEventObserver { _ , event ->

            if (event == Lifecycle.Event.ON_START) {

            } else if (event == Lifecycle.Event.ON_STOP) {

            }

        }
        // Add the observer to the lifecycle
        lifecycleOwner.lifecycle.addObserver(observer)
        // When the effect leaves the Composition, remove the observer
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {

        AppButton(text = "Start Service", onClick = {

        })

        AppButton(
            text = if (viewModel.viewState.value.isPlaying) { "Pause" } else { "Play" },
            onClick = {
                viewModel.apply {
                    if (viewState.value.isPlayServiceConnected) {
                        if (viewState.value.isPlaying) {
                            playerService?.pause()
                            onEvent(MainViewEvent.IsPlayerPlaying(isPlaying = false))
                        } else {
                            playerService?.play()
                            onEvent(MainViewEvent.IsPlayerPlaying(isPlaying = true))
                        }
                    }
                }
            })
        AppButton(text = "Stop", onClick = {
            viewModel.apply {
                onEvent(MainViewEvent.StopService)
            }
        })

    }
}