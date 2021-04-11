package com.mahmouddev.homeworktraining.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mahmouddev.homeworktraining.roomDB.dao.UserDao
import com.mahmouddev.homeworktraining.roomDB.entities.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun daoStudent(): UserDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                    return INSTANCE!!
                }
            } else {
                return INSTANCE!!
            }

        }


        fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "TrainingDB").build()
        }
    }
}