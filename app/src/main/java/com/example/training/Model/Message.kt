package com.example.training.Model

import androidx.compose.runtime.MutableState

data class Message(
    var text: String,
    val isUser: Boolean
)