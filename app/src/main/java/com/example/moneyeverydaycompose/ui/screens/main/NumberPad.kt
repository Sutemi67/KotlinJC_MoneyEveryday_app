package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
internal fun NumberPad(
    onNumberClick: (String) -> Unit,
    onClearClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        listOf(
            listOf("1", "4", "7"),
            listOf("2", "5", "8", "0"),
            listOf("3", "6", "9", "C")
        ).forEach { column ->
            Column {
                column.forEach { number ->
                    Button(
                        onClick = {
                            if (number == "C") onClearClick() else onNumberClick(number)
                        }
                    ) {
                        Text(text = number, fontSize = 25.sp)
                    }
                }
            }
        }
    }
}