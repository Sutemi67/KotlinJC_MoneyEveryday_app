package com.example.moneyeverydaycompose

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableLongState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyeverydaycompose.activity.HistoryScreen
import com.example.moneyeverydaycompose.activity.MainScreen
import com.example.moneyeverydaycompose.datastore.DataStoreManager
import com.example.moneyeverydaycompose.datastore.DateTextSettings
import com.example.moneyeverydaycompose.datastore.ResultTextSettings
import com.example.moneyeverydaycompose.ui.theme.MoneyEverydayComposeTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(this)
        val operationsData = InputDataStorage()

        setContent {
            MoneyEverydayComposeTheme {

                val monthlySummary = remember {
                    mutableIntStateOf(0)
                }

                val setDateOfClear = remember {
                    mutableLongStateOf(1111)
                }

                LaunchedEffect(key1 = true) {
                    dataStoreManager.getClearData().collect { setDate ->
                        setDateOfClear.longValue = setDate.dateOfClear
                    }
                }
                LaunchedEffect(key1 = true) {
                    dataStoreManager.getSummaryText().collect { settings ->
                        monthlySummary.intValue = settings.resultText
                    }
                }
                LaunchedEffect(key1 = true) {
                    dataStoreManager.getLists().collect { set ->
                        operationsData.operations = set.operations
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    TabScreen(dataStoreManager, monthlySummary, setDateOfClear, InputDataStorage())
                }
            }
        }
    }
}

@Composable
fun TabScreen(
    dataStoreManager: DataStoreManager,
    monthlySummary: MutableIntState,
    setDateOfClear: MutableLongState,
    dataStorage: InputDataStorage
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        "Главная",
        "История операций"
    )


    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> MainScreen(dataStoreManager, monthlySummary, setDateOfClear, dataStorage)
            1 -> HistoryScreen(dataStorage)
        }
    }
}





