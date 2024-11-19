package com.example.training

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object AppState{
    var userName: String = ""
    var isPremium: MutableState<Boolean> = mutableStateOf(false)
}
