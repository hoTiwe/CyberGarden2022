package com.example.oggetto.Model

class UserQuiz(
    var id: Int,
    var name: String,
    var login: String,
    var avatar: String,
    var about: String,
    var profession: Professions,
    var hobbies: List<Hobbie>,
    var links: Links
    )