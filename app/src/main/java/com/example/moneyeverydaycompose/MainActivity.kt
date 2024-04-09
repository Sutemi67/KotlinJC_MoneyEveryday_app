package com.example.moneyeverydaycompose

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
import com.example.moneyeverydaycompose.datastore.SettingsData
import com.example.moneyeverydaycompose.ui.theme.MoneyEverydayComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataDataStoreManager = DataStoreManager(this)

        setContent {
            MoneyEverydayComposeTheme {

                val monthlySummary = remember {
                    mutableIntStateOf(0)
                }
                LaunchedEffect(key1 = true) {
                    dataDataStoreManager.getSettings().collect { settings ->
                        monthlySummary.intValue = settings.resultText
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Income(dataDataStoreManager, monthlySummary)
                }
            }
        }
    }
}

@Composable
fun Income(dataStoreManager: DataStoreManager, monthlySummary: MutableState<Int>) {

    var input: String by remember { mutableStateOf("") }

    val coroutine = rememberCoroutineScope()
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


        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Button(onClick = { input += 1.toString() }) {
                    Text(text = "1")
                }
                Button(onClick = { input += 4.toString() }) {
                    Text(text = "4")
                }
                Button(onClick = { input += 7.toString() }) {
                    Text(text = "7")
                }
            }
            Column {
                Button(onClick = { input += 2.toString() }) {
                    Text(text = "2")
                }
                Button(onClick = { input += 5.toString() }) {
                    Text(text = "5")
                }
                Button(onClick = { input += 8.toString() }) {
                    Text(text = "8")
                }
                Button(onClick = { input += 0.toString() }) {
                    Text(text = "0")
                }
            }
            Column {
                Button(onClick = { input += 3.toString() }) {
                    Text(text = "3")
                }
                Button(onClick = { input += 6.toString() }) {
                    Text(text = "6")
                }
                Button(onClick = { input += 9.toString() }) {
                    Text(text = "9")
                }
                Button(onClick = { input = "" }) {
                    Text(text = "C")
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
                    if (input !="") {
                        monthlySummary.value += input.toInt()
                        coroutine.launch {
                            dataStoreManager.saveSettings(
                                SettingsData(
                                    monthlySummary.value
                                )
                            )
                        }
                    }
                    input = ""
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFF33B042))
            ) {
                Text(text = "Прибавить как прибыль")
            }
            Button(
                onClick = {
                    if (input != "") {
                        monthlySummary.value -= input.toInt()
                        coroutine.launch {
                            dataStoreManager.saveSettings(
                                SettingsData(
                                    monthlySummary.value
                                )
                            )
                        }
                    }
                    input = ""
                },
                Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color(0xFFB03358))
            ) {
                Text(text = "Вычесть как расходы")
            }
            Button(
                onClick = {
                        monthlySummary.value = 0
                        coroutine.launch {
                            dataStoreManager.saveSettings(
                                SettingsData(
                                    monthlySummary.value
                                )
                            )
                        }
                    input = ""
                },
                Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Color.LightGray)
            ) {
                Text(text = "Сбросить")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IncomePreview() {
    val dataDataStoreManager = DataStoreManager(MainActivity())
    MoneyEverydayComposeTheme {
        val monthlySummary = remember {
            mutableIntStateOf(0)
        }
        LaunchedEffect(key1 = true) {
            dataDataStoreManager.getSettings().collect { settings ->
                monthlySummary.intValue = settings.resultText
            }
        }
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Income(dataDataStoreManager, monthlySummary)
        }
    }
}