package com.example.oggetto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  networkservice {
    private var mInstance: networkservice? = null
    private val BASE_URL = "https://0109-2a00-1fa1-4199-993c-19a6-cedb-48a8-25c2.eu.ngrok.io" //адрес сервера

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}