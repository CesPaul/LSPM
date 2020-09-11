package com.cespaul.lspm.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cespaul.lspm.model.Item

@Database(entities = [Item::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun listDao(): ListDao
}