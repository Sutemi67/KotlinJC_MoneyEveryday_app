package com.example.moneyeverydaycompose

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.datastore.DataStoreManager
import com.example.moneyeverydaycompose.datastore.ResultTextSettings
import com.example.moneyeverydaycompose.datastore.DateTextSettings
import com.example.moneyeverydaycompose.ui.theme.MoneyEverydayComposeTheme
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.days

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)

        setContent {
            MoneyEverydayComposeTheme {

                val monthlySummary = remember {
                    mutableIntStateOf(0)
                }

                val dateOfClear = remember {
                    mutableLongStateOf(Calendar.getInstance().timeInMillis)
                }

                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSummaryText().collect { settings ->
                        monthlySummary.intValue = settings.resultText
                    }
                    dataStoreManager.getClearData().collect { setDate ->
                        dateOfClear.longValue = setDate.
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Income(dataStoreManager, monthlySummary, dateOfClear)
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
@Composable
fun Income(
    dataStoreManager: DataStoreManager,
    monthlySummary: MutableState<Int>,
    dateOfClear:MutableState<Long>
) {

    var input: String by remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()

    val current = Calendar.getInstance().timeInMillis
    val formatter = SimpleDateFormat("dd MMMM yyyy")
    val dateCurrent = formatter.format(current)


    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize(),
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
                text = "дата сброса", fontSize = 20.sp
            )
            Text(
                text = "${monthlySummary.value}",
                fontSize = 50.sp
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
                text = "сегодня:", fontSize = 20.sp
            )
            Text(
                text = "${monthlySummary.value}",
                fontSize = 50.sp
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
                text = "за месяц", fontSize = 20.sp
            )
            Text(
                text = "${monthlySummary.value}",
                fontSize = 50.sp
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
                text = "в день", fontSize = 20.sp
            )
            Text(
                text = "${monthlySummary.value / 30}",
                fontSize = 50.sp
            )
        }
        Text(
            text = input, fontSize = 30.sp
        )


        Spacer(modifier = Modifier.height(20.dp))
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
                            dataStoreManager.saveSummaryText(
                                ResultTextSettings(
                                    monthlySummary.value
                                )
                            )
                        }
                    } ?: {}
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
                            dataStoreManager.saveSummaryText(
                                ResultTextSettings(
                                    monthlySummary.value
                                )
                            )
                        }
                    } ?: {}
                    input = ""
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFB03358))
            ) {
                Text(text = "Вычесть как расходы", fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    monthlySummary.value = 0
                    coroutine.launch {
                        dataStoreManager.saveClearDate(DateTextSettings(current))
                        dataStoreManager.saveSummaryText(ResultTextSettings(monthlySummary.value))
                    }
                    input = ""
                },
                Modifier
                    .fillMaxWidth()
                    .padding(75.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Сбросить")
            }
        }
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun IncomePreview() {
//    val dataStoreManager = DataStoreManager(MainActivity())
//    MoneyEverydayComposeTheme {
//        val monthlySummary = remember {
//            mutableIntStateOf(0)
//        }
//        LaunchedEffect(key1 = true) {
//            dataStoreManager.getSummaryText().collect() { settings ->
//                monthlySummary.intValue = settings.resultText
//            }
//            dataStoreManager.getClearData().collect(){setData ->
//
//            }
//        }
//        Surface(
//            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
//        ) {
//            Income(dataStoreManager, monthlySummary)
//        }
//    }
//}