package com.app.alarmapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update




@Dao
interface AlarmDao {

    @Query("SELECT * FROM alarm")
    fun getAlarms(): LiveData<List<AlarmEntity>>

    @Query("Select is_enable from alarm where id = :alarmId")
    fun isEnabled(alarmId: Int) : Int

    @Insert
    suspend fun insert(data: AlarmEntity?)

    @Update
    suspend fun update(data: AlarmEntity?)
}