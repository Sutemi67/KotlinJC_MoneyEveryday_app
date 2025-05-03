package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.ui.theme.customType

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
                    FilledTonalButton(
                        modifier = Modifier.size(width = 80.dp, height = 50.dp).padding(1.dp),
                        onClick = {
                            if (number == "C") onClearClick() else onNumberClick(number)
                        }
                    ) {
                        Box(
                            Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = number, fontSize = 25.sp, style = customType.header)
                        }
                    }
                }
            }
        }
    }
}