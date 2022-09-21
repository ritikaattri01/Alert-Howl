package com.app.alarmapp.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm")
data class AlarmEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "is_enable") val isEnabled: Boolean? = null,
    @ColumnInfo(name = "time") val time: String? = null,
)