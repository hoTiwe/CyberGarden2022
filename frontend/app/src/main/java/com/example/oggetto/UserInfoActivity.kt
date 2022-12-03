package com.example.oggetto

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.oggetto.Model.Hobbie
import com.example.oggetto.Model.Quiz
import com.example.oggetto.Model.Session
import com.example.oggetto.Model.UserQuiz
import com.example.oggetto.api.networkservice
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserInfoActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getHobbies()
    }
    private fun getHobbies(){

        var session = Session(this)
        var token = session.getSession()
        Common.retrofitService.firstUser(token = token!!).enqueue(object : Callback<UserQuiz> {
            override fun onFailure(call: Call<UserQuiz>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<UserQuiz>, response: Response<UserQuiz>) {
                val quiz: UserQuiz? = response.body()
                println(quiz)
                println(networkservice.BASE_URL+"/images/"+quiz!!.avatar)
                if (quiz!=null){
                    Picasso.get()
                        .load(networkservice.BASE_URL+"/images/"+quiz!!.avatar)
                        .error(R.mipmap.ic_launcher)
                        .into(findViewById<ImageView>(R.id.imageKent));
                    findViewById<TextView>(R.id.job_title).text = quiz!!.profession.profession
                    findViewById<TextView>(R.id.fio_person).text = quiz!!.name
                    findViewById<TextView>(R.id.any_facts).text = quiz!!.about

                }
                else {
                    println("Не удалось получить квиз")
                }            }
        })
    }

}