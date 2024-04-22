package com.example.moneyeverydaycompose

import java.time.LocalDate

data class InputDataStorage(
   val operations: MutableList<String> = mutableListOf("","","","",""),
   val datesOfOperations: MutableList<LocalDate?> = mutableListOf(null),
   var operationIndex: Int = 0,
   var dateOfInput: LocalDate? = LocalDate.now()
)
