package com.example.smartdiscountcalculator.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.compose.rememberNavController
import com.example.smartdiscountcalculator.Routes
import com.example.smartdiscountcalculator.R

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun about(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(height = 20.dp))

        //profile image
        Image(
            painter = painterResource(R.drawable.picture),
            contentDescription = "Picture",
            modifier = Modifier.size(size = 100.dp)
        )
        Spacer(modifier = Modifier.height(height = 20.dp))

        //name and no matriks
        Text(
            text = "NUR FARIDATUL HANIS (F1032)",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(height = 16.dp))

        //description
        Text(
            text = "This app was developed for basic Android Studio practice using Kotlin",
            style = MaterialTheme.typography.headlineSmall
        )

        //navigate to MainActivity
        Button(
            onClick = {
                navController.navigate(Routes.MainActivity.route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Return to Main Screen")
        }
    }
}