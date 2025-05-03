package com.example.moneyeverydaycompose.ui.screens.history

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moneyeverydaycompose.database.AppDatabase

@Composable
fun HistoryScreen(context: Context) {
    val database = remember { AppDatabase.getDatabase(context) }
    val operations by database.operationDao().getAllOperations()
        .collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        ScreenTitle()

        if (operations.isEmpty()) {
            EmptyStateMessage()
        } else {
            OperationsList(operations)
        }
    }
}




