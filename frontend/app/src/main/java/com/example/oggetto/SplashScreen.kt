package com.example.oggetto

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.oggetto.Model.Session


class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        var session = Session(this)
        if (session.getSession() == null) {
            val intent = Intent(this, AuthActivity().javaClass)
            startActivity(intent)
        }
        else{
            val intent = Intent(this, EventsActivity().javaClass)
            startActivity(intent)
        }
        finish()
    }
}