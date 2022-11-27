package com.example.oggetto

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
import javax.security.auth.callback.CallbackHandler

class CreateEvent : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_event)
    }

    fun makeEvent(view : View){

        var inputEvent = findViewById<EditText>(R.id.inputEvent).text.toString()
        var inputLinkChat = findViewById<EditText>(R.id.inputLinkChat).text.toString()
        var inputAdress = findViewById<EditText>(R.id.inputAdress).text.toString()
        var inputdatastart = findViewById<EditText>(R.id.inputdatastart).text.toString()
        var inputdataend = findViewById<EditText>(R.id.inputdataend).text.toString()
        var inputOverInfo = findViewById<EditText>(R.id.inputOverInfo).text.toString()
        var session = Session(this)
        var token = session.getSession()
        if (inputEvent.isNotEmpty() && inputLinkChat.isNotEmpty() && inputdatastart.isNotEmpty()){
            findViewById<ConstraintLayout>(R.id.loading).visibility = View.VISIBLE
            Common.retrofitService.makeEvent(token!!, inputEvent, inputLinkChat,inputAdress,inputdatastart,inputdataend, inputOverInfo).enqueue(object :
                Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                    println(t.message)
                }
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    findViewById<ConstraintLayout>(R.id.loading).visibility = View.GONE
                    println("Удача")
                }
            })
        }
        else{
            findViewById<ConstraintLayout>(R.id.incorrect_data).visibility = View.VISIBLE
        }
    }
    fun back(view: View){ onBackPressed()}

}