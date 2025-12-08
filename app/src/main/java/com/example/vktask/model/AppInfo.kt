package com.example.vktask.model

data class AppInfo(
    val id: Int,
    val name: String,
    val description: String,
    val iconRes: Int,
    var installed: Boolean
)
