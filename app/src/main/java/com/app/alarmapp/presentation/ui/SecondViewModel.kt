package com.app.alarmapp.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.alarmapp.data.local.AlarmEntity
import com.app.alarmapp.data.local.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SecondViewModel @Inject constructor(db: AppDatabase) : ViewModel() {
    private val userDb = db.alarmDao

    fun insert(data: AlarmEntity) {
        viewModelScope.launch {
            userDb.insert(data)
        }
    }
}