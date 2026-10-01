package com.example.sumativa2mdgo.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sumativa2mdgo.components.Campo
import com.example.sumativa2mdgo.components.Resultado

@Composable
fun CalculadoraScreen(navController: NavController) {
    var platillo by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var porcentaje by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var mensaje by remember { mutableStateOf("") }

    val subtotal = (cantidad.toIntOrNull() ?: 0) * (precio.toFloatOrNull() ?: 0f)
    val propina = subtotal * ((porcentaje.toFloatOrNull() ?: 0f) / 100)
    val total = subtotal + propina

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row {
            Resultado("Propina", propina, Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Resultado("Total a pagar", total, Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Campo(platillo, { platillo = it }, "Platillo", error, KeyboardType.Text)
        Campo(cantidad, { cantidad = it }, "Cantidad", error, KeyboardType.Number)
        Campo(precio, { precio = it }, "Precio", error, KeyboardType.Decimal)
        Campo(porcentaje, { porcentaje = it }, "Propina %", error, KeyboardType.Decimal)

        if (error) {
            Text(text = mensaje)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (platillo.isEmpty() || cantidad.isEmpty() || precio.isEmpty() || porcentaje.isEmpty()) {
                    error = true
                    mensaje = "Llena todos los campos"
                } else if (porcentaje.toFloat() > 10) {
                    error = true
                    mensaje = "La propina maximo es 10"
                } else {
                    error = false
                    navController.navigate("resumen/$platillo/$propina/$total")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular total")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                platillo = ""
                cantidad = ""
                precio = ""
                porcentaje = ""
                error = false
                mensaje = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Limpiar")
        }
    }
}
