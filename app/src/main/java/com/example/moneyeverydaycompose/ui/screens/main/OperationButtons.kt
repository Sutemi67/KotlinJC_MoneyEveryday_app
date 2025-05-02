package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
private val GREEN_COLOR = Color(0xFF33B042)
private val RED_COLOR = Color(0xFFB03358)
@Composable
internal fun OperationButtons(
    input: String,
    onInputClear: () -> Unit,
    onSaveOperation: (Int, Boolean) -> Unit,
    onReset: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp, 10.dp)
    ) {
        Button(
            onClick = {
                input.toIntOrNull()?.let { amount ->
                    onSaveOperation(amount, true)
                    onInputClear()
                }
            },
            Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(GREEN_COLOR)
        ) {
            Text(text = "Прибавить как прибыль", fontSize = 15.sp)
        }

        Button(
            onClick = {
                input.toIntOrNull()?.let { amount ->
                    onSaveOperation(amount, false)
                    onInputClear()
                }
            },
            Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(RED_COLOR)
        ) {
            Text(text = "Вычесть как расходы", fontSize = 15.sp)
        }

        Spacer(modifier = Modifier.height(5.dp))

        Button(
            onClick = onReset,
            Modifier
                .fillMaxWidth()
                .padding(end = 200.dp),
            colors = ButtonDefaults.buttonColors(Color.LightGray)
        ) {
            Text(text = "Сбросить", fontSize = 10.sp)
        }
    }
}