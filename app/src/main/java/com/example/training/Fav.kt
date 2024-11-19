package com.example.training

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fav(val favNumber: Int,
               @PrimaryKey(autoGenerate = true)
               val id: Int = 0)
