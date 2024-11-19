package com.example.training

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Fav::class],
    version = 1
)
abstract class FavDatabase: RoomDatabase() {
    abstract fun contentDao():com.example.training.Dao


    companion object {
        @Volatile
        private var INSTANCE: FavDatabase? = null

        fun getDatabase(context: Context): FavDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}