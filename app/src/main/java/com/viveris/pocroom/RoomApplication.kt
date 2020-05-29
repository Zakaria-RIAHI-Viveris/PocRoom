package com.viveris.pocroom

import android.app.Application
import androidx.room.Room
import com.viveris.pocroom.db.AppRoomDatabase

class RoomApplication : Application() {

    lateinit var appRoomDatabase: AppRoomDatabase

    override fun onCreate() {
        super.onCreate()
        appRoomDatabase = Room.databaseBuilder(
                applicationContext,
                AppRoomDatabase::class.java, AppRoomDatabase.DATABASE_NAME
        ).build()
    }

}