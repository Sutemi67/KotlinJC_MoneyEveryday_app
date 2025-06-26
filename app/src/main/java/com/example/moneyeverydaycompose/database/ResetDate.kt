package com.example.moneyeverydaycompose.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reset_date")
data class ResetDate(
    @PrimaryKey
    val id: Int = 1,
    val dateInMillis: Long
) 