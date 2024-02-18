package com.istudio.services.main.states

sealed class MainViewEvent {
    data class PlayServiceStatus(val isConnected: Boolean=false) : MainViewEvent()
    data class IsPlayerPlaying(val isPlaying: Boolean=false) : MainViewEvent()
    data object StopService : MainViewEvent()
}