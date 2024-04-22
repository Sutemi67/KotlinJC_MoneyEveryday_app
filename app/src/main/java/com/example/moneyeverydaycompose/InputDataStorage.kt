package com.example.moneyeverydaycompose

import java.time.LocalDate

data class InputDataStorage(
   val operations: MutableList<String?> = mutableListOf("0", null, null, null),
   val datesOfOperations: MutableList<String?> = mutableListOf("0", null, null, null),
   var operationIndex: Int = 0,
   var dateOfInput: LocalDate? = LocalDate.now()
)
