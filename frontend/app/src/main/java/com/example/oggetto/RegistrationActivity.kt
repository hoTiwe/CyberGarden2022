package com.example.oggetto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.oggetto.Model.User

class RegistrationActivity : AppCompatActivity() {
    val user = User(null, null,null,null,null,null, mutableSetOf(),null,null,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
    }
}