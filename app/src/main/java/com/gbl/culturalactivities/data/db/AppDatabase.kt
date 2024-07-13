package com.gbl.culturalactivities.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [CulturalActivityDto::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun culturalActivitiesDao(): CulturalActivitiesDao

    companion object {
        private const val DB_NAME = "cultural_base"

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase = instance ?: synchronized(this) {
            Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DB_NAME
            ).build().also { instance = it }
        }
    }
}