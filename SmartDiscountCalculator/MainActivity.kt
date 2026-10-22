package com.example.smartdiscountcalculator.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.smartdiscountcalculator.R
import com.example.smartdiscountcalculator.Routes
import com.example.smartdiscountcalculator.Routes.ResultActivity
import com.example.smartdiscountcalculator.Routes.AboutActivity
import com.example.smartdiscountcalculator.ui.theme.SmartDiscountCalculatorTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SmartDiscountCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) { }
                    ScreenMain()
                }
            }
        }
    }
}

@Composable
// navigate page
fun ScreenMain(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = Routes.MainActivity.route,
        modifier = modifier) {
        // MainActivity
        composable(Routes.MainActivity.route) {
            Interface(navController = navController)
        }

        composable(AboutActivity.route) {
            about(modifier = modifier)
        }

        // passing argument to ResultActivity page
        composable(ResultActivity.route + "/{oriPrice}/{discount}/{item}/{isChecked}") { navBackStack ->
            val oriPrice = navBackStack.arguments?.getString("oriPrice")
            val discount = navBackStack.arguments?.getString("discount")
            val item = navBackStack.arguments?.getString("item")
            val isChecked = navBackStack.arguments?.getString("isChecked")
            displayAfterDiscount(
                oriPrice = oriPrice?.toDoubleOrNull(),
                discount = discount?.toDoubleOrNull(),
                item = item,
                sst = isChecked,
                navController = navController
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Interface(navController: NavHostController, modifier: Modifier = Modifier) {

    var price by remember { mutableStateOf("") }
    var discount by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(value = false) }
    var item by remember { mutableStateOf("") }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 24.dp))
        
        //title
        Text(text = "SMART DISCOUNT CALCULATOR", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(height = 16.dp))

        //EditText: Enter price
        OutlinedTextField(
            value = price,
            onValueChange = {
                price = it
            },
            label = { Text(text = "Original Price (in RM)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 16.dp))

        //EditText: Enter discount
        OutlinedTextField(
            value = discount,
            onValueChange = { discount = it },
            label = { Text(text = "Discount (%)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(height = 16.dp))

        //Radio Button
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = item == "Electronic Item",
                onClick = { item = "Electronic Item" })
            Text(text = "Electronic Item")
            Spacer(modifier = Modifier.width(width = 16.dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = item == "Clothing Item",
                onClick = { item = "Clothing Item" })
            Text(text = "Clothing Item")
            Spacer(modifier = Modifier.width(width = 16.dp))
        }

        // show image when radio button selected
        if (item == "Electronic Item") {
            Image(
                painter = painterResource(R.drawable.electronic),
                contentDescription = "Electronic Item",
                modifier = Modifier.size(size = 100.dp)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.clothing),
                contentDescription = "Clothing Item",
                modifier = Modifier.size(size = 100.dp)
            )
        }

        // checkbox
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = isChecked, onCheckedChange = { isChecked = it })
            Text(text = "Add SST Tax 6%")
        }
        Spacer(modifier = Modifier.height(height = 24.dp))

        //Calculate Now button enable when user enter price and discount
        Button(
            onClick = {
                val safeItem = item.replace(" ", "")
                navController.navigate(ResultActivity.route + "/$price/$discount/$safeItem/$isChecked")
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = discount.isNotEmpty() && price.isNotEmpty()
        ) {
            Text(text = "Calculate Now")
        }

        // button navigate to About App
        Button(
            onClick = {
            navController.navigate(AboutActivity.route)
            },
            modifier = Modifier.fillMaxWidth()
            ) {
            Text(text = "About App")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewInterface() {
    SmartDiscountCalculatorTheme {
        Interface(
            navController = rememberNavController(),
            modifier = Modifier
        )
    }
}