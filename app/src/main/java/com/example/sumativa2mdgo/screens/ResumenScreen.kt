package com.example.sumativa2mdgo.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun ResumenScreen(navController: NavController, platillo: String, propina: Float, total: Float) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Platillo: $platillo")
        Text("Propina: $" + "%.2f".format(propina))
        Text("Total pagado: $" + "%.2f".format(total))
        Button(onClick = { navController.popBackStack() }) {
            Text("Volver a calcular")
        }
    }
}
