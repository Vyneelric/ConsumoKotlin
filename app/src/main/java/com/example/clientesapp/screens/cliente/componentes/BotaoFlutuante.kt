package com.example.clientesapp.screens.cliente.componentes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.clientesapp.ui.theme.ClientesAPPTheme

@Composable
fun BotaoFlutuante(controleNavegacao: NavHostController?) {
    FloatingActionButton(
        onClick = {
            controleNavegacao!!.navigate(route = "cadastro")
        }
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Botão adicionar"
        )
    }
}

@Preview
@Composable
private fun BotaoFlutuantePreview() {
    ClientesAPPTheme {
        BotaoFlutuante(null)
    }
}