package com.istudio.services.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.services.main.states.MainDataState
import com.istudio.services.main.states.MainViewEvent
import com.istudio.services.modules.bound_services.media_player.services.PlayerService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( ) : ViewModel() {

    // Holds the data of all the widgets in the view
    var viewState: MutableState<MainDataState> = mutableStateOf(MainDataState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<MainViewEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    var playerService: PlayerService?=null


    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: MainViewEvent) {
        viewModelScope.launch {
            when (event) {
                is MainViewEvent.PlayServiceStatus -> {
                    viewState.value = viewState.value.copy(isPlayServiceConnected = event.isConnected)
                }

                is MainViewEvent.IsPlayerPlaying -> {
                    viewState.value = viewState.value.copy(
                        isPlaying = event.isPlaying, playerStopped = false
                    )
                }

                is MainViewEvent.StopService -> {
                    viewState.value = viewState.value.copy(playerStopped = true)
                }

                else -> {}
            }
        }
    }


}