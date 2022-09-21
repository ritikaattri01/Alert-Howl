package com.app.alarmapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AlarmReceiver : BroadcastReceiver() {
//    var mp: MediaPlayer? = null
    override fun onReceive(context: Context?, intent: Intent?) {
//        val alarmSound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
//        mp = MediaPlayer.create(context, alarmSound)
//        mp!!.isLooping = true
//        mp!!.start()
    val i = Intent(context, AlarmService::class.java)
    context!!.startService(i)
    val notificationUtils = NotificationUtils(context)
    val notification = notificationUtils.getNotificationBuilder().build()
    notificationUtils.getManager().notify(150, notification)
}
}