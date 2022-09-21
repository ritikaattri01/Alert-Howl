package com.app.alarmapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.data.local.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(db: AppDatabase) : ViewModel() {
    private val userDb = db.alarmDao

    fun getAlarms(): LiveData<List<AlarmEntity>> {
        return userDb.getAlarms()
    }

    fun update(data: AlarmEntity) {
        viewModelScope.launch {
            userDb.update(data)
        }
    }
}