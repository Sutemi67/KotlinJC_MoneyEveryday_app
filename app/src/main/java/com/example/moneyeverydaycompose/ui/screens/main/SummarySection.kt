package com.example.moneyeverydaycompose.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.ui.theme.customType

@Composable
internal fun SummarySection(label: String, value: Int) {
    val textColor =
        if (value >= 0) Color.Green else Color.Red
    val textStyle = customType.label

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            style = textStyle
        )
        Text(
            text = "%,d".format(value),
            fontSize = 50.sp,
            color = textColor,
            style = textStyle
        )
    }
}