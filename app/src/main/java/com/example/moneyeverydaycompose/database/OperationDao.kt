package com.example.moneyeverydaycompose.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {
    @Query("SELECT * FROM operations ORDER BY id DESC")
    fun getAllOperations(): Flow<List<Operation>>

    @Insert
    suspend fun insertOperation(operation: Operation)

    @Delete
    suspend fun deleteOperation(operation: Operation)

    @Query("DELETE FROM operations")
    suspend fun deleteAllOperations()
} 