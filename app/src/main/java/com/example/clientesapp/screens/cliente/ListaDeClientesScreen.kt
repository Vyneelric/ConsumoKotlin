package com.example.clientesapp.screens.cliente

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clientesapp.screens.cliente.componentes.BarraDeTitulo
import com.example.clientesapp.screens.cliente.componentes.BarraInferior
import com.example.clientesapp.screens.cliente.componentes.BotaoFlutuante
import com.example.clientesapp.screens.cliente.componentes.Conteudo
import com.example.clientesapp.ui.theme.ClientesAPPTheme

@Composable
fun ListaDeClientes(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            BarraDeTitulo()
        },
        bottomBar = {
            BarraInferior()
        },
        floatingActionButton = {
            BotaoFlutuante()
        },
        content = { padding ->
            Conteudo(padding)
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ListaDeClientesPreview() {
    ClientesAPPTheme {
        ListaDeClientes()
    }
}