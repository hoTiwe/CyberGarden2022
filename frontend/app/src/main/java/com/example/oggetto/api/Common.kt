package com.example.oggetto.api

object Common {
    val retrofitService: JSONPlaceHolderApi
        get() = networkservice.getClient().create(JSONPlaceHolderApi::class.java)
}