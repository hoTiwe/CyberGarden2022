package com.example.oggetto

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oggetto.Model.Professions
import com.example.oggetto.Model.Quiz
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuizActivity: AppCompatActivity() {
    var quiz: Quiz? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setQuiz()

    }
    private fun setQuiz(){
        Common.retrofitService.firstQuiz().enqueue(object : Callback<Quiz> {
            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                quiz = response.body()!!
                println(quiz)
            }
        })
        if (quiz!=null) setContentView(R.layout.activity_quiz_1)
        else Toast.makeText(QuizActivity(), "Упс.. Что-то пошло не так", Toast.LENGTH_SHORT).show()
    }
}