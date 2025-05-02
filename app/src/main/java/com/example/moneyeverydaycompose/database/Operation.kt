package com.example.moneyeverydaycompose.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operations")
data class Operation(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val date: String,
    val amount: Int
) 