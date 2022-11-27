package com.example.oggetto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.oggetto.Model.Event
import com.example.oggetto.Model.Hobbie
import com.example.oggetto.Model.Quiz
import com.example.oggetto.Model.Session
import com.example.oggetto.adapter.RecyclerViewAdapter
import com.example.oggetto.api.networkservice
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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