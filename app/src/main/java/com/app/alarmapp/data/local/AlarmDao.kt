package com.app.alarmapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAlarms(): LiveData<List<AlarmEntity>>

    @Insert
    suspend fun insert(data: AlarmEntity?)

}