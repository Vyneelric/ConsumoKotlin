package com.example.clientesapp.screens.cliente

import android.content.res.Configuration
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.clientesapp.model.Cliente
import com.example.clientesapp.service.Conexão
import com.example.clientesapp.ui.theme.ClientesAPPTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await

@Composable
fun ClienteForm(padding: PaddingValues, controleNavegacao: NavHostController?) {

    var nomeCliente by remember { mutableStateOf("") }
    var emailCliente by remember { mutableStateOf("") }

    var isNomeError by remember { mutableStateOf(false) }
    var isEmailError by remember { mutableStateOf(false) }

    fun validar(): Boolean{
        isNomeError = nomeCliente.length < 3
        isEmailError = !Patterns.EMAIL_ADDRESS.matcher(emailCliente).matches()

        return !isNomeError && !isEmailError
    }

    var mostrarMensagemSucesso by remember { mutableStateOf(false) }

    var clienteAPI = Conexão().getClienteService()

    Surface(
        modifier = Modifier.fillMaxSize()
            .padding(padding)
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    MaterialTheme
                        .colorScheme
                        .primaryContainer
                )
        ){
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Pessoa"
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Novo cliente",
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = nomeCliente,
                onValueChange = {
                    nomeCliente = it
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                label = {
                    Text(text = "Nome do cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                isError = isNomeError,
                supportingText = {
                    if(isNomeError){
                        Text(text = "O campo nome do cliente está incorreto!")
                    }
                }
            )

            OutlinedTextField(
                value = emailCliente,
                onValueChange = {
                    emailCliente = it
                },
                modifier = Modifier.padding(16.dp).fillMaxWidth(),
                label = {
                    Text(text = "E-mail do cliente")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Done
                ),
                isError = isEmailError,
                supportingText = {
                    if(isNomeError){
                        Text(text = "O E-mail do cliente está incorreto!")
                    }
                }
            )

            Button(
                onClick = {
                    if(validar()){
                        val cliente = Cliente(
                            id = null,
                            nome = nomeCliente,
                            email = emailCliente
                        )
                        GlobalScope.launch(Dispatchers.IO){
                            val clienteNovo = clienteAPI
                                .cadastrarCliente(cliente)
                                .await()
                            mostrarMensagemSucesso = true
                        }
                    } else {
                        println("Dados incorretos!!!")
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Gravar Cliente")
            }
        }

        if (mostrarMensagemSucesso){
            AlertDialog(
                onDismissRequest = {
                    mostrarMensagemSucesso = true
                    nomeCliente = ""
                    emailCliente = ""
                },
                title = {
                    Text("Sucesso!")
                },
                text = {
                    Text(text = "Cliente $nomeCliente gravado com sucesso! \nDeseja cadastrar outro cliente?")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            nomeCliente = ""
                            emailCliente = ""
                            mostrarMensagemSucesso = false
                        }
                    ){
                      Text(text = "Sim")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            controleNavegacao!!.navigate("home")
                        }
                    ){
                        Text(text = "Não")
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun ClienteFormPreview() {
    ClientesAPPTheme {
        ClienteForm(PaddingValues(0.dp), null)
    }
}