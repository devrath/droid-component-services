package com.droid.sample.utils.extensions.activity

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.DisplayMetrics

fun Activity.isInPortrait(): Boolean {
    val displayMetrics = DisplayMetrics()
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.heightPixels > displayMetrics.widthPixels
}

fun Context.isMyServiceRunning(serviceClass: Class<*>): Boolean {
    val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    return manager.getRunningServices(Integer.MAX_VALUE)
        .any { it.service.className == serviceClass.name }
}