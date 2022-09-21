package com.app.alarmapp.util

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings


class AlarmService : Service() {
    var mp: MediaPlayer? = null
    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(applicationContext, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mp?.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        mp?.start()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}