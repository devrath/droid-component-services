package com.istudio.services.modules.bound_services.media_player.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.istudio.services.R


class PlayerService : Service() {

    val TAG = PlayerService::class.java.name
    lateinit var mediaPlayer : MediaPlayer

    val mBinder : IBinder = LocalBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"OnCreate called")
        mediaPlayer = MediaPlayer.create(this, R.raw.pikachu)
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG,"onBind called")
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy called")
        mediaPlayer.release()
    }

    /**
     * <*********************>Class communicates between view and the service<*********************>
     */
    inner class LocalBinder : Binder() {
        // Return this instance of MyService so clients can call public methods
        val service: PlayerService
            get() = this@PlayerService
    }
    /**
     * <*********************>Class communicates between view and the service<*********************>
     */


    /**
     * <*********************>Client methods bound to service<*********************>
     */
    fun play(){
        mediaPlayer.start()
    }

    fun pause(){
        mediaPlayer.pause()
    }

    fun isPlaying() =  mediaPlayer.isPlaying
    /**
     * <*********************>Client methods bound to service<*********************>
     */

}