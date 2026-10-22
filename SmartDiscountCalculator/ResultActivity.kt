package com.example.smartdiscountcalculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import com.example.smartdiscountcalculator.Routes.MainActivity

@Composable
//terima argument dari page MainActivity
fun displayAfterDiscount(
    oriPrice: Double?,
    discount: Double?,
    item: String?,
    sst: String?,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val sst = sst?.toBoolean()
    val oriPrice = oriPrice ?: 0.0
    val discount = discount ?: 0.0
    val disPercent = discount / 100
    val priceAfterDiscount = oriPrice - (oriPrice * disPercent)
    val sstPrice = priceAfterDiscount + (oriPrice * 0.06)
    val finalPrice = priceAfterDiscount

    //Display
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 24.dp))

        Text(text = "SMART DISCOUNT CALCULATOR", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(text = "Display:", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(text = "Original Price: $oriPrice")
        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(text = "Discount Percentage: $disPercent")
        Spacer(modifier = Modifier.height(height = 16.dp))

        Text(text = "Item Type: $item")
        Spacer(modifier = Modifier.height(height = 16.dp))

        Column {
            if (sst == true) {
                Text(text = "SST 6% is applied")
                Spacer(modifier = Modifier.height(height = 16.dp))

                Text(text = "Final Price = $sstPrice")
                Spacer(modifier = Modifier.height(height = 24.dp))
            } else {
                Text(text = "SST is not applied")
                Spacer(modifier = Modifier.height(height = 16.dp))

                Text(text = "Final Price = $finalPrice")
                Spacer(modifier = Modifier.height(height = 24.dp))
            }
        }

        //navigate to MainActivity page
        Button(
            onClick = {
                navController.navigate(MainActivity.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Return to Main Screen")
        }
    }
}