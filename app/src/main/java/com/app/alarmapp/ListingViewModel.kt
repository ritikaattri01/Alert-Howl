package com.app.alarmapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.data.local.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(db: AppDatabase) : ViewModel() {
    private val userDb = db.alarmDao

    fun getAlarms(): LiveData<List<AlarmEntity>> {
        return userDb.getAlarms()
    }
}