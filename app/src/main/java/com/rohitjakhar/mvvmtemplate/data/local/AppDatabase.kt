package com.rohitjakhar.mvvmtemplate.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rohitjakhar.mvvmtemplate.data.local.model.DataEntity

@Database(entities = [DataEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AppDao
}
