package com.example.oggetto

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oggetto.Model.Event
import com.example.oggetto.Model.Session
import com.example.oggetto.adapter.RecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventsActivity  : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oggetto_events)
        initEvents(this)
    }
    private fun initEvents(context: Context){
        var session = Session(this)
        var token = session.getSession()
        println("Session ${token}")
        findViewById<ConstraintLayout>(R.id.loading).visibility = View.VISIBLE
        Common.retrofitService.getEventsList(token = token!!).enqueue(object :
            Callback<List<Event>> {
            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                println(t.message)
            }
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                var eventList = response.body()
                if (eventList!=null) {
                    var rec = findViewById<RecyclerView>(R.id.recyclerItems)
                    rec.layoutManager = LinearLayoutManager(EventsActivity())
                    rec.adapter = RecyclerViewAdapter(eventList, context)
                }
                else{
                    Toast.makeText(context, "Упс.. Что-то пошло не так", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun newEvent(view: View){
        val intent = Intent(this, CreateEvent().javaClass)
        startActivity(intent)
    }
    fun back(vie: View){
        onBackPressed()
    }
}