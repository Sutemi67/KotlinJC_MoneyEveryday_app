package com.example.moneyeverydaycompose

import java.time.LocalTime
import java.time.format.DateTimeFormatter


data class InputDataStorage(
   var operations: MutableList<String> = mutableListOf("","","","","","","","","",""),
   val datesOfOperations: MutableList<String> = mutableListOf("","","","","","","","","",""),
   val time:String = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
)
