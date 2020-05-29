package com.viveris.pocroom.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.viveris.pocroom.metier.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun utilisateurDao(): UserDao

    companion object {
        const val DATABASE_NAME = "app-room.db"
    }
}