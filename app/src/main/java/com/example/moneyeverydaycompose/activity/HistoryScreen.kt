package com.example.moneyeverydaycompose.activity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.InputDataStorage

@Composable
fun HistoryScreen(
    dataStorage: InputDataStorage
) {

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Последние операции:",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[0], fontSize = 24.sp)
            Text(text = dataStorage.datesOfOperations[0], fontSize = 24.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[1], fontSize = 22.sp)
            Text(text = dataStorage.datesOfOperations[1], fontSize = 22.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[2], fontSize = 20.sp)
            Text(text = dataStorage.datesOfOperations[2], fontSize = 20.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[3], fontSize = 18.sp)
            Text(text = dataStorage.datesOfOperations[3], fontSize = 18.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[4], fontSize = 16.sp)
            Text(text = dataStorage.datesOfOperations[4], fontSize = 16.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[5], fontSize = 14.sp)
            Text(text = dataStorage.datesOfOperations[5], fontSize = 14.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[6], fontSize = 12.sp)
            Text(text = dataStorage.datesOfOperations[6], fontSize = 12.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[7], fontSize = 10.sp)
            Text(text = dataStorage.datesOfOperations[7], fontSize = 10.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[8], fontSize = 8.sp)
            Text(text = dataStorage.datesOfOperations[8], fontSize = 8.sp)
        }
        Row(
            Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = dataStorage.operations[9], fontSize = 6.sp)
            Text(text = dataStorage.datesOfOperations[9], fontSize = 6.sp)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(InputDataStorage())
}