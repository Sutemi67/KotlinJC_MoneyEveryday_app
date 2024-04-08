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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moneyeverydaycompose.ui.theme.MoneyEverydayComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyEverydayComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Income()
                }
            }
        }
    }
}

@Composable
fun Income() {
    var summary by remember { mutableIntStateOf(0) }
    var input by remember { mutableStateOf("") }
    Column(
        Modifier
            .fillMaxSize()
            .wrapContentSize()
    ) {
        Text(
            text = "$summary", Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = input, Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Row(
            modifier = Modifier
                .wrapContentSize()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column {
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "1")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "3")
                }
            }
            Column {
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "1")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
            }
            Column {
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "1")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
            }

        }
        Column {
            Button(
                onClick = { summary += input.toInt() }, Modifier.fillMaxWidth()
            ) {
                Text(text = "Прибавить как прибыль")
            }
            Button(
                onClick = { summary -= input.toInt() }, Modifier.fillMaxWidth()
            ) {
                Text(text = "Вычесть как расходы")
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    MoneyEverydayComposeTheme {
        Income()
    }
}