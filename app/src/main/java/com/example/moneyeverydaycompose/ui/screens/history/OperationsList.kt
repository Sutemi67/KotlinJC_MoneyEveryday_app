package com.example.moneyeverydaycompose.ui.screens.history

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.moneyeverydaycompose.database.Operation

@Composable
internal fun OperationsList(operations: List<Operation>) {
    LazyColumn {
        items(operations) { operation ->
            OperationCard(operation)
        }
    }
}