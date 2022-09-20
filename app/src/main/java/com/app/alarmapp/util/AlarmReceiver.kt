package com.app.alarmapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri


class AlarmReceiver : BroadcastReceiver() {
    var mp: MediaPlayer? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        mp = MediaPlayer.create(context, alarmSound)
        mp!!.isLooping = true
        mp!!.start()
        val notificationUtils = NotificationUtils(context)
        val notification = notificationUtils.getNotificationBuilder().build()
        notificationUtils.getManager().notify(150, notification)
    }
}