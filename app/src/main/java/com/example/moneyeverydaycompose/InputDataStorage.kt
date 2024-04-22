package com.example.moneyeverydaycompose

import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class InputDataStorage(
   val operations: MutableList<String> = mutableListOf("потрачено 3353 денег","потрачено 3353 денег","потрачено 3353 денег","потрачено 3353 денег","потрачено 3353 денег","потрачено 3353 денег"),
   val datesOfOperations: MutableList<String> = mutableListOf("22:30","22:30","22:30","22:30","22:30","22:30"),
   val time:String = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))
)
