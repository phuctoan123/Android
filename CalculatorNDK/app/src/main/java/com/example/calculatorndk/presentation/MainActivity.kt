package com.example.calculatorndk.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorndk.ui.theme.CalculatorNDKTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorNDKTheme {
                CalculatorApp(viewModel)
            }
        }
    }
}


@Composable
fun CalculatorApp(viewModel: MainViewModel) {

    val mainState by viewModel.mainState.collectAsState()
    val onEvent = viewModel::onEvent

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {

        Box {
            BasicTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Medium
                ),
                readOnly = true,
                value = mainState.result,
                onValueChange = {}
            )
            IconButton(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {
                    onEvent(MainEvent.OnBackClicked)
                }) {
                Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back")
            }
        }
        CalculatorButtonGrid(onEvent){
            onEvent(MainEvent.OnNumClicked(it))
        }


    }
}

@Composable
fun CalculatorButtonGrid(onEvent: (mainEvent: MainEvent) -> Unit,
                         onButtonClick: (String) -> Unit) {
    val modifier =
    Column {
        Row {
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                    text = "7") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "8") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "9") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "+") {
                onEvent(MainEvent.OnOperatorClicked(it))
            }
        }
        Row {
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "4"
            ) {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "5"
            ) {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "6 "
            ) {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "-"
            ) {
                onEvent(MainEvent.OnOperatorClicked(it))
            }
        }
        Row {
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "1") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "2") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "3") {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "*") {
                onEvent(MainEvent.OnOperatorClicked(it))
            }
        }
        Row {
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "C"
            ) {
                onEvent(MainEvent.OnClearClicked)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "0"
            ) {
                onButtonClick(it)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "="
            ) {
                onEvent(MainEvent.OnEqualsClicked)
            }
            CalculatorButton(
                modifier = Modifier.weight(1f).padding(4.dp),
                text = "/"
            ) {
                onEvent(MainEvent.OnOperatorClicked(it))
            }
        }
    }
}


@Composable
fun CalculatorButton(modifier: Modifier = Modifier, text: String, onClick: (String) -> Unit) {
    Button(
        modifier = modifier,
        onClick = {
            onClick(text)
        }
    ) {
        Text(text = text,
            fontSize = 28.sp)
    }
}