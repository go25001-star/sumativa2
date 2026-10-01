package com.example.sumativa2mdgo.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun Resultado(titulo: String, valor: Float, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = titulo)
        Text(text = "$" + "%.2f".format(valor))
    }
}
@Composable
fun Campo(valor: String, cambio: (String) -> Unit, texto: String, error: Boolean, teclado: KeyboardType) {
    OutlinedTextField(
        value = valor,
        onValueChange = cambio,
        label = { Text(text = texto) },
        isError = error,
        keyboardOptions = KeyboardOptions(keyboardType = teclado),
        modifier = Modifier.fillMaxWidth()
    )
}
