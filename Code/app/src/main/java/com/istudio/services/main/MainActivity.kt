package com.istudio.services.main

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.istudio.services.main.states.MainViewEvent
import com.istudio.services.module_selection.ModuleDemo
import com.istudio.services.module_selection.ModuleSelectionScreen
import com.istudio.services.modules.bound_services.media_player.MediaPlayerScreen
import com.istudio.services.modules.bound_services.media_player.services.PlayerService
import com.istudio.services.modules.bound_services.media_player.services.PlayerService.LocalBinder
import com.istudio.services.modules.started_services.intent_service.IntentServiceScreen
import com.istudio.services.modules.started_services.job_intent_service.JobIntentServiceScreen
import com.istudio.services.modules.started_services.job_scheduler.JobSchedularScreen
import com.istudio.services.modules.started_services.normal_service.NormalServiceScreen
import com.istudio.services.ui.theme.CodeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()


    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
            // Handle the connection to the service
            viewModel.onEvent(MainViewEvent.PlayServiceStatus(isConnected = true))
            iBinder?.let {
                // Get the binder instance from the service
                val localBinder = iBinder as LocalBinder
                viewModel.playerService = localBinder.service

                viewModel.playerService?.let {
                    if(it.isPlaying()){
                        viewModel.onEvent(MainViewEvent.IsPlayerPlaying(true))
                    }
                }
            }
        }

        /**
         * Note:- On calling the UnBind Service, onServiceDisconnected is not called
         */
        override fun onServiceDisconnected(componentName: ComponentName?) {
            // Handle the disconnection from the service
            viewModel.onEvent(MainViewEvent.PlayServiceStatus(isConnected = false))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodeTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = ModuleDemo.DemoSelection.rout
                    ) {
                        // Selection Screen
                        composable(ModuleDemo.DemoSelection.rout) { ModuleSelectionScreen(navController = navController) }
                        // Normal Service
                        composable(ModuleDemo.NormalServiceScreen.rout) { NormalServiceScreen(navController = navController) }
                        // Intent Service
                        composable(ModuleDemo.IntentServiceScreen.rout) { IntentServiceScreen(navController = navController) }
                        // Job Scheduler
                        composable(ModuleDemo.JobSchedularScreen.rout) { JobSchedularScreen(navController = navController) }
                        // Job Intent service
                        composable(ModuleDemo.JobIntentServiceScreen.rout) { JobIntentServiceScreen(navController = navController) }
                        // Media player
                        composable(ModuleDemo.MediaPlayerServiceScreen.rout) { MediaPlayerScreen(navController = navController) }
                    }
                }
            }
        }

        bindServiceForComponent(isBind = true)
    }

    override fun onDestroy() {
        super.onDestroy()
        // Unbind service
        if(viewModel.viewState.value.isPlayServiceConnected){
            // unbind the service
            bindServiceForComponent(isBind = false)
            // Set the flag that service is not connected
            viewModel.onEvent(MainViewEvent.PlayServiceStatus(isConnected = false))
        }
    }


    /**
     * Bind and Unbind from one place
     */
    private fun bindServiceForComponent(isBind:Boolean) {
        if(isBind){
            // Bind to the service
            bindService(Intent(this, PlayerService::class.java), serviceConnection, Context.BIND_AUTO_CREATE)
        }else{
            // Unbind the service
            unbindService(serviceConnection)
        }
    }
}