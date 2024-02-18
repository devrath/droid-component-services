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

    private val mBinder : IBinder = LocalBinder()

    private lateinit var mediaPlayer : MediaPlayer

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"OnCreate called")
        start()
    }

    override fun onBind(intent: Intent?): IBinder {
        Log.d(TAG,"onBind called")
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy called")
        stop()
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
    private fun start() { mediaPlayer = MediaPlayer.create(this, R.raw.pikachu) }
    fun play() { mediaPlayer.start() }
    fun pause() { mediaPlayer.pause() }
    private fun stop() { mediaPlayer.release() }
    fun isPlaying() =  mediaPlayer.isPlaying
    /**
     * <*********************>Client methods bound to service<*********************>
     */

}