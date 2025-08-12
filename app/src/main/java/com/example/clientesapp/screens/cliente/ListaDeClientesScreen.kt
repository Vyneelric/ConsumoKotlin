package com.example.clientesapp.screens.cliente

import android.content.res.Configuration
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clientesapp.screens.cliente.componentes.BarraDeTitulo
import com.example.clientesapp.screens.cliente.componentes.BarraInferior
import com.example.clientesapp.screens.cliente.componentes.BotaoFlutuante
import com.example.clientesapp.screens.cliente.componentes.Conteudo
import com.example.clientesapp.ui.theme.ClientesAPPTheme

@Composable
fun ListaDeClientes(modifier: Modifier = Modifier) {

    var controleNavegacao = rememberNavController()

    Scaffold(
        topBar = {
            BarraDeTitulo()
        },
        bottomBar = {
            BarraInferior(controleNavegacao)
        },
        floatingActionButton = {
            BotaoFlutuante(controleNavegacao)
        },
        content = { padding ->
            NavHost(
                navController = controleNavegacao,
                startDestination = "home"
            ){
                composable(route = "home"){ Conteudo(padding) }
                composable(route = "cadastro"){ ClienteForm(padding, controleNavegacao) }
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListaDeClientesPreview() {
    ClientesAPPTheme {
        ListaDeClientes()
    }
}