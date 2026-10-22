package com.example.jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack.ui.theme.JetPackTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetPackTheme {
                Scaffold (
                    topBar = {
                        TopAppBar(title = { Text("Food Order Preferences") })
                    }
                ) { paddingValues ->
                    PreferenceContent(modifier = Modifier.padding(paddingValues))
                }
            }
            ImageTheme {
                NasiLemak()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreferenceContent(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }

    val options = listOf("Telur Mata", "Kerang Sambal", "Ayam Goreng")
    var selectedOption by remember { mutableStateOf(options[0]) }

    var showLabel by remember { mutableStateOf(false) }
    var resultText by remember { mutableStateOf("Your Choosen Preferences Will Shown Here") }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Your Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 20.dp))

        Text("Your Preference:", style = MaterialTheme.typography.titleMedium)

        options.forEach { text ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { selectedOption = text }
                    )
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = { selectedOption = text }
                )
                Text(text = text, modifier = Modifier.padding(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = showLabel,
                onCheckedChange = { showLabel = it }
            )
            Text("Show Output")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                val showResult = when (selectedOption) {
                    "Telur Mata" -> "Nasi Lemak Telur Mata"
                    "Kerang Sambal" -> "Nasi Lemak Kerang Sambal"
                    "Ayam Goreng" -> "Nasi Lemak Ayam Goreng"
                    else -> ""
                }

                if (showLabel) {
                    resultText = "Name: $name | Preference: $showResult"
                } else {
                    resultText = "Checkbox is not checked!"
                }
                      },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("SUBMIT")
        }
        Spacer(modifier = Modifier.height(30.dp))

        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = resultText,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(40.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NasiLemak() {
    Image(
        painter = painterResource(R.drawable.NasiLemak),
        contentDescription = "Image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewNasiLemak() {
    NasiLemak()
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreferencePreview() {
    JetPackTheme {
        Scaffold (
            topBar = {
                TopAppBar(title = { Text("Food Order Preference") })
            }
        ) { paddingValues ->
            PreferenceContent(modifier = Modifier.padding(paddingValues))
        }
    }
}