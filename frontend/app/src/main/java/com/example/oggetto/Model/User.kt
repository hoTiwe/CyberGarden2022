package com.example.oggetto.Model

import java.io.File

data class User (
    var email: String? = null,
    var password: String? = null,
    var name: String? = null,
    var profession: Int? = null,
    var wordAboutSelf: String? = null,
    var image: File? = null,
    var hobbies: List<Int>  = listOf(),
    var link_inst: String? = null,
    var link_tg: String? = null,
    var link_vk: String? = null
)