@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mytemperatureconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mytemperatureconverter.ui.theme.MyTemperatureConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTemperatureConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        StatefulTemperatureInput()
                        ConverterApp()
                        TwoWayConverterApp()
                    }
                }
            }
        }
    }
}


//stateful codelab
@Composable
fun StatefulTemperatureInput(
    modifier: Modifier = Modifier,
) {

    var input by remember { mutableStateOf("") }
    var output by remember { mutableStateOf("") }

    Column(
        modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stateful_converter),
            style = MaterialTheme.typography.headlineSmall,
        )

        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                input = it
                output = convertoFahrenheit(it)
            },
        )

        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}

private fun convertoFahrenheit(celsius: String) =
    celsius.toDoubleOrNull()?.let {
        (it * 9 / 5) + 32
    }.toString()


//stateless codelab
@Composable
fun StatelessTemperaturInput(
    input: String,
    output: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier.padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.stateless_converter),
            style = MaterialTheme.typography.headlineSmall,
        )

        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_celsius)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )

        Text(text = stringResource(id = R.string.temperature_fahrenheit, output))
    }
}


//compose yang memanggil stateless
@Composable
fun ConverterApp(
    modifier: Modifier = Modifier
) {
    var input by remember {
        mutableStateOf("")
    }
    var output by remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        StatelessTemperaturInput(
            input = input,
            output = output,
            onValueChange = {
                input = it
                output = convertoFahrenheit(it)
            },
        )
    }
}

@Composable
fun GeneralTemperatureInput(
    scale: Scale,
    input: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = input,
            label = { Text(text = stringResource(id = R.string.enter_celsius, scale.scaleNamee)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = onValueChange,
        )
    }
}

@Composable
private fun TwoWayConverterApp(modifier: Modifier = Modifier) {
    var celsius by remember {
        mutableStateOf("")
    }
    var fahrenheit by remember {
        mutableStateOf("")
    }
    Column(modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.two_way_converter),
            style = MaterialTheme.typography.headlineSmall
        )
        GeneralTemperatureInput(
            scale = Scale.CELSIUS,
            input = celsius,
            onValueChange = {
                celsius = it
                fahrenheit = convertoFahrenheit(it)
            },
        )

        GeneralTemperatureInput(
            scale = Scale.FAHRENHEIT,
            input = fahrenheit,
            onValueChange = {
                fahrenheit = it
                celsius = convertoCelsius(it)
            },
        )
    }
}

fun convertoCelsius(it: String): String =
    it.toDoubleOrNull()?.let {
        (it - 32) * 5 / 9
    }.toString()


enum class Scale(val scaleNamee: String) {
    CELSIUS("Celsius"),
    FAHRENHEIT("Fahrenheit")
}



