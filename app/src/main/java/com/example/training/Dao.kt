package com.example.training

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface Dao {
    @Insert
    suspend fun favNumber(fav: Fav)

    @Query("Select * From Fav ORDER BY id DESC")
    fun getFavNumber(): List<Fav>
}

