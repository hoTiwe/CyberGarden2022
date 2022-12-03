package com.example.oggetto

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.oggetto.Model.Event
import com.example.oggetto.adapter.RecyclerViewAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_to_event)
        var id = intent.getStringExtra("id").toString()
        initActivity(id)
    }

    fun initActivity(id : String){
        findViewById<ConstraintLayout>(R.id.loading).visibility = View.VISIBLE
        Common.retrofitService.getEvent(id).enqueue(object :
            Callback<Event> {
            override fun onFailure(call: Call<Event>, t: Throwable) {
                findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                println(t.message)
            }
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                var event = response.body()
                if (event!=null) {
                    findViewById<TextView>(R.id.textTitle).text = event.title
                    findViewById<TextView>(R.id.textDescribe).text = event.description
                    findViewById<TextView>(R.id.text_chat).text = event.chat
                    findViewById<TextView>(R.id.text_data).text = event.startDate.toString()
                    findViewById<TextView>(R.id.text_address).text = event.adress

                }
                else{
                    Toast.makeText(EventsActivity(), "Упс.. Что-то пошло не так", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
    fun back(view: View){
        onBackPressed()
    }
}