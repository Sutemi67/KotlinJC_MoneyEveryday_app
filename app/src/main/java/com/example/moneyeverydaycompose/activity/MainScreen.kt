package com.example.moneyeverydaycompose.activity

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.database.AppDatabase
import com.example.moneyeverydaycompose.database.Operation
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    context: Context
) {
    val coroutine = rememberCoroutineScope()
    val database = remember { AppDatabase.getDatabase(context) }

    var monthlySummary by remember { mutableIntStateOf(0) }
    var setDateOfClear by remember { mutableLongStateOf(Calendar.getInstance().timeInMillis) }
    var input: String by remember { mutableStateOf("") }

    val current = Calendar.getInstance().timeInMillis
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    val daysPass = ((current - setDateOfClear) / (1000 * 60 * 60 * 24)) + 1

    fun savingData(amount: Int, isIncome: Boolean) {
        coroutine.launch {
            try {
                val operation = Operation(
                    description = if (isIncome) "Заработано $amount денег" else "Потрачено $amount денег",
                    date = formatter.format(current),
                    amount = if (isIncome) amount else -amount
                )
                database.operationDao().insertOperation(operation)
                monthlySummary += if (isIncome) amount else -amount
                Log.d("database", "Операция успешно сохранена: ${operation.description}")
            } catch (e: Exception) {
                Log.e("database", "Ошибка при сохранении операции: ${e.message}")
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Сегодня:", fontSize = 20.sp
            )
            Text(
                text = formatter.format(current),
                fontSize = 20.sp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Дата сброса", fontSize = 12.sp
            )
            Text(
                text = formatter.format(setDateOfClear),
                fontSize = 12.sp
            )
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Дней прошло:", fontSize = 12.sp
            )
            Text(
                text = "$daysPass",
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "Итог за период:", fontSize = 18.sp
            )
            Text(
                text = "%,d".format(monthlySummary),
                fontSize = 50.sp
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp, 0.dp),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "В среднем в день",
                fontSize = 18.sp
            )
            Text(
                text = "%,d".format(monthlySummary / daysPass),
                fontSize = 50.sp
            )
        }
        Text(text = input, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Button(onClick = { input += "1" }) {
                    Text(text = "1", fontSize = 25.sp)
                }
                Button(onClick = { input += "4" }) {
                    Text(text = "4", fontSize = 25.sp)
                }
                Button(onClick = { input += "7" }) {
                    Text(text = "7", fontSize = 25.sp)
                }
            }
            Column {
                Button(onClick = { input += "2" }) {
                    Text(text = "2", fontSize = 25.sp)
                }
                Button(onClick = { input += "5" }) {
                    Text(text = "5", fontSize = 25.sp)
                }
                Button(onClick = { input += "8" }) {
                    Text(text = "8", fontSize = 25.sp)
                }
                Button(onClick = { input += "0" }) {
                    Text(text = "0", fontSize = 25.sp)
                }
            }
            Column {
                Button(onClick = { input += "3" }) {
                    Text(text = "3", fontSize = 25.sp)
                }
                Button(onClick = { input += "6" }) {
                    Text(text = "6", fontSize = 25.sp)
                }
                Button(onClick = { input += "9" }) {
                    Text(text = "9", fontSize = 25.sp)
                }
                Button(onClick = { input = "" }) {
                    Text(text = "C", fontSize = 25.sp)
                }
            }

        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp)
        ) {
            Button(
                onClick = {
                    input.toIntOrNull()?.let { amount ->
                        savingData(amount, true)
                        input = ""
                    }
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF33B042))
            ) {
                Text(text = "Прибавить как прибыль", fontSize = 15.sp)
            }
            Button(
                onClick = {
                    input.toIntOrNull()?.let { amount ->
                        savingData(amount, false)
                        input = ""
                    }
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFB03358))
            ) {
                Text(text = "Вычесть как расходы", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = {
                    coroutine.launch {
                        try {
                            database.operationDao().deleteAllOperations()
                            monthlySummary = 0
                            setDateOfClear = current
                            Log.d("database", "Все операции удалены")
                        } catch (e: Exception) {
                            Log.e("database", "Ошибка при сбросе данных: ${e.message}")
                        }
                    }
                    input = ""
                },
                Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 200.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Сбросить", fontSize = 10.sp)
            }
        }
    }
}
