package com.mtnine.dohwaji.database

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomDatabaseModule {
    private lateinit var mainDatabase: MainDatabase

    @Singleton
    @Provides
    fun providesMainDatabase(baseApplication: Application, databaseCallback: RoomDatabase.Callback): MainDatabase {
        mainDatabase = Room.databaseBuilder(
            baseApplication.applicationContext,
            MainDatabase::class.java,
            "main_db"
        )
            .addCallback(databaseCallback)
            .build()
        return mainDatabase
    }

    @Singleton
    @Provides
    fun providesMainDao(mainDatabase: MainDatabase) = mainDatabase.getMainDao()


    @Singleton
    @Provides
    fun providesDatabaseCallback(): RoomDatabase.Callback {
        return object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                CoroutineScope(Dispatchers.IO).launch {
                    mainDatabase.clearAllTables()
                }
            }
        }
    }
}