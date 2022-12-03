package com.example.oggetto.Model

import java.util.Date

class Event(
    var id: Int,
    var title: String,
    var description: String,
    var adress: String,
    var startDate: Date,
    var endDate: Date?,
    var chat: String?,
    var subscribers: List<UserQuiz>,
    var creator: UserQuiz
)