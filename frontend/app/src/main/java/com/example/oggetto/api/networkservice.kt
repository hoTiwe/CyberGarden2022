package com.example.oggetto.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  networkservice {
    private var mInstance: networkservice? = null
    private val BASE_URL = "https://f0a0-2a00-1fa1-4199-993c-44cd-c5c8-9e32-b645.eu.ngrok.io/" //адрес сервера

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