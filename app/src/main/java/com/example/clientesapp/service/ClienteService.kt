package com.example.clientesapp.service

import com.example.clientesapp.model.Cliente
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClienteService {
    //o cliente incial é o objeto/class que ele vai pedir, agora o Cliente da frente é oq vai ser mandado
    @POST("clientes")
    fun cadastrarCliente(@Body cliente: Cliente): Call<Cliente>

    @GET("clientes")
    fun listarTodos(): Call<List<Cliente>>

    @GET("clientes/{id}")
    fun listarPorID(@Path("id") id: Long): Call<Cliente>

    @PUT("clientes")
    fun atualizarCliente(@Body cliente: Cliente): Call<Cliente>

    @DELETE("clientes")
    fun deletarCliente(@Body cliente: Cliente): Unit
}