package com.app.alarmapp.util

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.app.alarmapp.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val application = context?.applicationContext as? Application
        val alarmDao = AppModule().provideDataBase(application!!).alarmDao
        val alarmId = intent?.getIntExtra("alarmId", 0) ?: 0
        val i = Intent(context, AlarmService::class.java)
        i.action = AlarmService.ACTION_START_FOREGROUND_SERVICE

        GlobalScope.launch {
            if(alarmDao.isEnabled(alarmId) == 1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(i)
                }else{
                    context.startService(i)
                }
            }
        }


    }

}