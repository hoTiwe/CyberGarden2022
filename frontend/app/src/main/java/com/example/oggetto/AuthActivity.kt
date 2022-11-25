package com.example.oggetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
    }
    fun openRegistration(view: View){
        val intent = Intent(this, RegistrationActivity().javaClass)
        startActivity(intent)
    }
}