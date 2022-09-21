package com.app.alarmapp.util

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.Settings
import android.util.Log


class AlarmService : Service() {
    var mp: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        mp = MediaPlayer.create(applicationContext, Settings.System.DEFAULT_ALARM_ALERT_URI)
        mp?.isLooping = true
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmId = intent?.getIntExtra("alarmId", 0)
        if (intent != null) {
            val action = intent.action
            when (action) {
                ACTION_START_FOREGROUND_SERVICE -> {
                    val notificationUtils = NotificationUtils(applicationContext)
                    val notification = notificationUtils.getNotificationBuilder().build()
                    notificationUtils.getManager().notify(alarmId!!, notification)
                    startForeground(alarmId, notification)
                    mp?.start()
                }
                ACTION_STOP_FOREGROUND_SERVICE -> stopForegroundService()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun stopForegroundService() {
        stopForeground(true)
        stopSelf()
        mp?.stop()
    }

//    private fun startForegroundService() {
//        Log.d(TAG_FOREGROUND_SERVICE, "Stop foreground service.")
//
//        // Stop foreground service and remove the notification.
//        stopForeground(true)
//
//        // Stop the foreground service.
//        stopSelf()
//    }

    companion object {
        val TAG_FOREGROUND_SERVICE = "FOREGROUND_SERVICE"

        val ACTION_START_FOREGROUND_SERVICE = "ACTION_START_FOREGROUND_SERVICE"

        val ACTION_STOP_FOREGROUND_SERVICE = "ACTION_STOP_FOREGROUND_SERVICE"
    }
}