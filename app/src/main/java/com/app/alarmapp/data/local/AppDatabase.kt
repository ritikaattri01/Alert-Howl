package com.app.alarmapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.alarmapp.data.local.AppDatabase.Companion.DB_VERSION

@Database(
    entities = [AlarmEntity::class],
    version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract val alarmDao: AlarmDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "my_db"
    }
}