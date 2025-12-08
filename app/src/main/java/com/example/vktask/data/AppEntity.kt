package com.example.vktask.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val installed: Boolean = false
)
