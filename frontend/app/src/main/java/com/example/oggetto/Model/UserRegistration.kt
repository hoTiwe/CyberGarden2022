package com.example.oggetto.Model

import retrofit2.http.Multipart

class UserRegistration (
    val email: String,
    val password: String,
    val name: String,
    val about: String,
    val profession: Int,
    val link_inst: String,
    val link_tg: String,
    val link_vk: String,
    val hobbies: Array<Int>,
    val avatar: Multipart
    )