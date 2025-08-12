package com.example.clientesapp.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Conexão {

    private val BASEURL = "https://srv945707.hstgr.cloud/api/"

    private val conexao = Retrofit
        .Builder()
        .baseUrl(BASEURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getClienteService(): ClienteService{
        return conexao.create(ClienteService::class.java)
    }
}