package com.example.pettyhub

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Petdata::class], exportSchema = false, version = 1)
abstract class AppDatabase:RoomDatabase() {
    abstract fun getDao():Dao
}