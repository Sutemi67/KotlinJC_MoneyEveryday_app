package com.example.moneyeverydaycompose.ui.screens.history

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.database.Operation
import com.example.moneyeverydaycompose.ui.theme.AppColors
import com.example.moneyeverydaycompose.ui.theme.customType

@Composable
internal fun OperationCard(
    operation: Operation
) {
    val bg = if (operation.amount > 0) AppColors.GREEN_COLOR_20 else AppColors.RED_COLOR_20

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp),
        colors = CardDefaults.cardColors(
            containerColor = bg
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = operation.description,
                    fontSize = 16.sp,
                    style = customType.label
                )
                Text(
                    text = operation.date,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                    style = customType.label
                )
            }
            Text(
                text = "${operation.amount} ₽",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                style = customType.header
            )
        }
    }
}