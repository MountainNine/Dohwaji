package com.mtnine.dohwaji.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mtnine.dohwaji.model.Post

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun getMainDao(): MainDao
}