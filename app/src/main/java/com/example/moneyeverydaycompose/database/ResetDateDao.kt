package com.example.moneyeverydaycompose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResetDateDao {
    @Query("SELECT * FROM reset_date WHERE id = 1")
    fun getResetDate(): Flow<ResetDate?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResetDate(resetDate: ResetDate)
} 