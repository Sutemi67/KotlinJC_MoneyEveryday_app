package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.ui.theme.AppColors.GREEN_COLOR_100
import com.example.moneyeverydaycompose.ui.theme.AppColors.RED_COLOR_100
import com.example.moneyeverydaycompose.ui.theme.customType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun OperationButtons(
    input: String,
    onInputClear: () -> Unit,
    onSaveOperation: (Int, Boolean) -> Unit,
    onReset: () -> Unit
) {
    var alertDialog by remember { mutableStateOf(false) }
    val textStyle = customType.body
    Column(
        Modifier
            .fillMaxWidth()
            .padding(30.dp, 10.dp)
    ) {
        ElevatedButton(
            onClick = {
                input.toIntOrNull()?.let { amount ->
                    onSaveOperation(amount, true)
                    onInputClear()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = GREEN_COLOR_100,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            )
        ) {
            Text(text = "Прибавить как прибыль", fontSize = 15.sp, style = textStyle)
        }

        ElevatedButton(
            onClick = {
                input.toIntOrNull()?.let { amount ->
                    onSaveOperation(amount, false)
                    onInputClear()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = RED_COLOR_100,
                contentColor = Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 10.dp
            )
        ) {
            Text(text = "Вычесть как расходы", fontSize = 15.sp, style = textStyle)
        }

        Spacer(modifier = Modifier.height(5.dp))
        Row {
            ElevatedButton(
                onClick = { alertDialog = true },
            ) {
                Text(text = "Сбросить", fontSize = 10.sp, style = textStyle)
            }
            Spacer(Modifier.weight(1f))
        }

        if (alertDialog) {
            AlertDialog(
                onDismissRequest = { alertDialog = false },
                confirmButton = {
                    Text(
                        modifier = Modifier.clickable {
                            alertDialog = false
                            onReset()
                        },
                        text = "Сбросить"
                    )
                },
                dismissButton = {
                    Text(
                        modifier = Modifier.clickable {
                            alertDialog = false
                        },
                        text = "Отмена"
                    )
                },
                text = {
                    Text(
                        "Действительно хотите сбросить все данные?",
                        style = customType.header
                    )
                }
            )
        }
    }
}