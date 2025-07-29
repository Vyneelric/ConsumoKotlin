package com.example.clientesapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Conexão {

    private val BASEURL = "https://app1.celso.dev.br/clientes-app/api/"

    private val conexao = Retrofit
        .Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClienteService(): ClienteService{
        return conexao.create(ClienteService::class.java)
    }
}