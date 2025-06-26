package com.example.moneyeverydaycompose.ui.screens.main

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moneyeverydaycompose.database.AppDatabase
import com.example.moneyeverydaycompose.database.Operation
import com.example.moneyeverydaycompose.database.ResetDate
import com.example.moneyeverydaycompose.ui.theme.customType
import kotlinx.coroutines.launch

private const val TAG = "MainScreen"
private val DATE_FORMAT = SimpleDateFormat("dd MMMM yyyy")
private const val MILLISECONDS_IN_DAY = 24 * 60 * 60 * 1000L

@Composable
fun MainScreen(context: Context) {
    val coroutine = rememberCoroutineScope()
    val database = remember { AppDatabase.getDatabase(context) }
    val operations =
        database.operationDao().getAllOperations().collectAsState(initial = emptyList())
    val resetDate = database.resetDateDao().getResetDate().collectAsState(initial = null)

    var monthlySummary by remember { mutableIntStateOf(0) }
    var setDateOfClear by remember { mutableLongStateOf(Calendar.getInstance().timeInMillis) }
    var input by remember { mutableStateOf("") }

    val current = Calendar.getInstance().timeInMillis

    LaunchedEffect(resetDate.value) {
        setDateOfClear = resetDate.value?.dateInMillis ?: current
    }

    val daysPass = ((current - setDateOfClear) / MILLISECONDS_IN_DAY).toInt() + 1

    LaunchedEffect(operations.value) {
        monthlySummary = operations.value.sumOf { it.amount }
    }

    fun savingData(amount: Int, isIncome: Boolean) {
        coroutine.launch {
            try {
                val operation = Operation(
                    description = if (isIncome) "Заработано $amount денег" else "Потрачено $amount денег",
                    date = DATE_FORMAT.format(current),
                    amount = if (isIncome) amount else -amount
                )
                database.operationDao().insertOperation(operation)
                Log.d(TAG, "Операция успешно сохранена: ${operation.description}")
            } catch (e: Exception) {
                Log.e(TAG, "Ошибка при сохранении операции: ${e.message}")
            }
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DateInfoRow("Сегодня:", DATE_FORMAT.format(current))
        DateInfoRow("Дата сброса", DATE_FORMAT.format(setDateOfClear))
        DateInfoRow("Дней прошло:", "$daysPass")

        Spacer(modifier = Modifier.height(10.dp))

        SummarySection("Итог за период:", monthlySummary)
        SummarySection("В среднем в день", if (daysPass > 0) monthlySummary / daysPass else 0)

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            value = input,
            onValueChange = { input = it },
            enabled = false,
            readOnly = true,
            singleLine = true,
            label = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Поле для ввода",
                    style = customType.body
                )
            },
            textStyle = customType.header.copy(textAlign = TextAlign.End)
        )

        Spacer(modifier = Modifier.height(5.dp))

        NumberPad(
            onNumberClick = { input += it },
            onClearClick = { input = "" }
        )

        OperationButtons(
            input = input,
            onInputClear = { input = "" },
            onSaveOperation = { amount, isIncome -> savingData(amount, isIncome) },
            onReset = {
                coroutine.launch {
                    try {
                        database.operationDao().deleteAllOperations()
                        monthlySummary = 0
                        setDateOfClear = current
                        database.resetDateDao().insertResetDate(ResetDate(dateInMillis = current))
                        Log.d(TAG, "Все операции удалены и дата сброса обновлена")
                    } catch (e: Exception) {
                        Log.e(TAG, "Ошибка при сбросе данных: ${e.message}")
                    }
                }
            }
        )
    }
}

