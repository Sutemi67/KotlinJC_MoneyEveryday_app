package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.ui.theme.customType

@Composable
internal fun DateInfoRow(label: String, value: String) {
    val textStyle = customType.header
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = if (label == "Сегодня:") 20.sp else 12.sp, style = textStyle)
        Text(text = value, fontSize = if (label == "Сегодня:") 20.sp else 12.sp, style = textStyle)
    }
}