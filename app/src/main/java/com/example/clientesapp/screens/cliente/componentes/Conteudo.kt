package com.example.clientesapp.screens.cliente.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.clientesapp.model.Cliente
import com.example.clientesapp.service.ClienteService
import com.example.clientesapp.service.Conexão
import com.example.clientesapp.ui.theme.ClientesAPPTheme
import kotlinx.coroutines.Dispatchers
import retrofit2.await

@Composable
fun Conteudo(paddingValues: PaddingValues) {

    val clienteApi = Conexão().getClienteService()

    var clientes by remember { mutableStateOf(listOf<Cliente>()) }

    LaunchedEffect(Dispatchers.IO) {
        clientes = clienteApi.listarTodos().await()
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ){
            Icon(
                modifier = Modifier
                    .padding(6.dp),
                imageVector = Icons.Default.Person,
                contentDescription = "Person"
            )
            Text(text = "Lista de clientes")
        }
        LazyColumn(
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            items(clientes){ cliente ->
                CardCliente(cliente, clienteApi)
            }
        }
    }
}

@Composable
private fun CardCliente(
    cliente: Cliente,
    clienteApi: ClienteService
) {

    var mostrarConfirmacaoExclusao by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                bottom = 8.dp
            )
            .fillMaxWidth()
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(cliente.nome)
                Text(cliente.email)
            }
            IconButton(
                onClick = {
                    mostrarConfirmacaoExclusao = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
    if (mostrarConfirmacaoExclusao) {
        AlertDialog(
            onDismissRequest = {
                mostrarConfirmacaoExclusao = false
            },
            dismissButton = {
                mostrarConfirmacaoExclusao = false
            },
            confirmButton = {

            },
            title = {
                Text(text = "Exclusão do cliente")
            },
            text = {
                Text(text = "Confirma a exclusão do cliente abaixo?\n\n${cliente.nome}")
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Cuidado"
                )
            }
        )
    }
}

@Preview
@Composable
private fun ConteudoPreview() {
    ClientesAPPTheme {
        Conteudo(paddingValues = PaddingValues(16.dp))
    }
}