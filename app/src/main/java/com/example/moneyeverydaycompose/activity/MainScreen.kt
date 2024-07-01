package com.example.moneyeverydaycompose.activity

import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.InputDataStorage
import com.example.moneyeverydaycompose.datastore.DataStoreManager
import com.example.moneyeverydaycompose.datastore.DateTextSettings
import com.example.moneyeverydaycompose.datastore.ResultTextSettings
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    dataStoreManager: DataStoreManager,
    monthlySummary: MutableState<Int>,
    setDateOfClear: MutableState<Long>,
    dataStorage: InputDataStorage
) {

    var input: String by remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()

    val current = Calendar.getInstance().timeInMillis
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    val daysPass = ((current - setDateOfClear.value) / (1000 * 60 * 60 * 24)) + 1


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
                text = formatter.format(setDateOfClear.value),
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
                text = "%,d".format((monthlySummary.value)),
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
                text = "%,d".format(monthlySummary.value / daysPass),
                fontSize = 50.sp
            )
        }
        Text(
            text = input, fontSize = 30.sp
        )


        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Button(onClick = { input += 1.toString() }) {
                    Text(text = "1", fontSize = 25.sp)
                }
                Button(onClick = { input += 4.toString() }) {
                    Text(text = "4", fontSize = 25.sp)
                }
                Button(onClick = { input += 7.toString() }) {
                    Text(text = "7", fontSize = 25.sp)
                }
            }
            Column {
                Button(onClick = { input += 2.toString() }) {
                    Text(text = "2", fontSize = 25.sp)
                }
                Button(onClick = { input += 5.toString() }) {
                    Text(text = "5", fontSize = 25.sp)
                }
                Button(onClick = { input += 8.toString() }) {
                    Text(text = "8", fontSize = 25.sp)
                }
                Button(onClick = { input += 0.toString() }) {
                    Text(text = "0", fontSize = 25.sp)
                }
            }
            Column {
                Button(onClick = { input += 3.toString() }) {
                    Text(text = "3", fontSize = 25.sp)
                }
                Button(onClick = { input += 6.toString() }) {
                    Text(text = "6", fontSize = 25.sp)
                }
                Button(onClick = { input += 9.toString() }) {
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
                    input.toIntOrNull()?.let {
                        monthlySummary.value += it
                        coroutine.launch {
                            dataStoreManager.saveSummaryText(ResultTextSettings(monthlySummary.value))
                            dataStoreManager.saveToDataStore(InputDataStorage(dataStorage.operations))
                        }
                    } ?: {}
                    dataStorage.operations.add(0, "Заработано $input денег")
                    if (dataStorage.operations.size > 10) dataStorage.operations.removeAt(
                        dataStorage.operations.size - 1
                    )
                    dataStorage.datesOfOperations.add(0, dataStorage.time)
                    if (dataStorage.datesOfOperations.size > 10) dataStorage.datesOfOperations.removeAt(
                        dataStorage.datesOfOperations.size - 1
                    )
                    input = ""

                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF33B042))
            ) {
                Text(text = "Прибавить как прибыль", fontSize = 15.sp)
            }
            Button(
                onClick = {
                    input.toIntOrNull()?.let {
                        monthlySummary.value -= it
                        coroutine.launch {
                            dataStoreManager.saveSummaryText(ResultTextSettings(monthlySummary.value))
                            dataStoreManager.saveToDataStore(InputDataStorage(dataStorage.operations))
                        }
                    } ?: {}
                    dataStorage.operations.add(0, "Потрачено $input денег")
                    if (dataStorage.operations.size > 10) dataStorage.operations.removeAt(
                        dataStorage.operations.size - 1
                    )
                    dataStorage.datesOfOperations.add(0, dataStorage.time)
                    if (dataStorage.datesOfOperations.size > 10) dataStorage.datesOfOperations.removeAt(
                        dataStorage.datesOfOperations.size - 1
                    )
                    input = ""
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFB03358))
            ) {
                Text(text = "Вычесть как расходы", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = {
                    monthlySummary.value = 0
                    setDateOfClear.value = current

                    coroutine.launch {
                        dataStoreManager.saveSummaryText(ResultTextSettings(monthlySummary.value))
                    }
                    coroutine.launch {
                        dataStoreManager.saveClearDate(DateTextSettings(setDateOfClear.value))
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
