package com.example.moneyeverydaycompose.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.R

data class AppType(
    val header: TextStyle,
    val headerMini: TextStyle,
    val body: TextStyle,
    val label: TextStyle,
)

val fonts = FontFamily(
    Font(R.font.comfortaa_variable_font_wght, FontWeight.Thin),
    Font(R.font.comfortaa_variable_font_wght, FontWeight.Normal),
    Font(R.font.comfortaa_variable_font_wght, FontWeight.SemiBold),
    Font(R.font.comfortaa_variable_font_wght, FontWeight.Bold),
)

val customType = AppType(
    header = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
    ),
    headerMini = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
    ),
    body = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),
    label = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Thin,
        fontSize = 12.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
    ),
)