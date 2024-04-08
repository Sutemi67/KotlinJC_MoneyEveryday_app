package com.example.moneyeverydaycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import com.example.moneyeverydaycompose.ui.theme.MoneyEverydayComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyEverydayComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
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
    Column (
        Modifier.wrapContentSize(Alignment.Center)
    ){
        Text(
            text = "$summary"
        )
        Text(
            text = input
        )
        Row (
            Modifier.wrapContentSize(Alignment.Center)
        ){
            Column {
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "1")
                }
                Button(onClick = { input += Button.NUMBER1.toString() }) {
                    Text(text = "2")
                }
            }
            Column {
                Button(onClick = {summary+=input.toInt()}) {
                    Text(text = "Прибавить как прибыль")
                }
                Button(onClick = {summary-=input.toInt()}) {
                    Text(text = "Вычесть как расходы")
                }
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