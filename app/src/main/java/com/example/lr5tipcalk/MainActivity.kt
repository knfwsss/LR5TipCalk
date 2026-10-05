package com.example.lr5tipcalk

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lr5tipcalk.ui.theme.LR5TipCalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LR5TipCalkTheme {
                TipScreen()
            }
        }
    }
}

@Composable
fun TipScreen(modifier: Modifier = Modifier) {
    var orderSum by remember { mutableStateOf("") }
    var dishCount by remember { mutableStateOf("") }
    var tipPercent by remember { mutableStateOf(0f) }
    var selectedDiscount by remember { mutableStateOf<Int?>(null) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            Text("Сумма заказа:")
            TextField(
                value = orderSum,
                onValueChange = { orderSum = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Количество блюд:",
                modifier = Modifier.padding(top = 12.dp)
            )
            TextField(
                value = dishCount,
                onValueChange = { dishCount = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "Чаевые:",
                modifier = Modifier.padding(top = 12.dp)
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("0")
                Slider(
                    value = tipPercent,
                    onValueChange = { tipPercent = it },
                    valueRange = 0f..25f,
                    modifier = Modifier.weight(1f)
                )
                Text("25")
            }
            Text(
                text = "Скидка:",
                modifier = Modifier.padding(top = 12.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                listOf(3, 5, 7, 10).forEach { percent ->
                    RadioButton(
                        selected = selectedDiscount == percent,
                        onClick = { selectedDiscount = percent }
                    )
                    Text("$percent%")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TipPreview() {
    LR5TipCalkTheme {
        TipScreen()
    }
}
