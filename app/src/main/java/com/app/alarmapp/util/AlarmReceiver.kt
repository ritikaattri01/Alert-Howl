package com.app.alarmapp.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmId = intent?.getIntExtra("alarm", 0)
        val i = Intent(context, AlarmService::class.java)
        i.putExtra("alarmId", alarmId)
        context!!.startService(i)
    }
}