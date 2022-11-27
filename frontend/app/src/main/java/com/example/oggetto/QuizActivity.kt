package com.example.oggetto

import Common
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.oggetto.Model.Hobbie
import com.example.oggetto.Model.Quiz
import com.example.oggetto.Model.Session
import com.example.oggetto.api.networkservice
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class QuizActivity: AppCompatActivity() {
    var quiz: Quiz? = null
    var count = 3
    var isLastLayout = false
    private var x1 = 0f
    private var x2 = 0f
    val MIN_DISTANCE = 150
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setQuiz()
    }
    private fun setQuiz(){
        count--
        var session = Session(this)
        var token = session.getSession()
        Common.retrofitService.firstQuiz(token = token!!).enqueue(object : Callback<Quiz> {
            override fun onFailure(call: Call<Quiz>, t: Throwable) {
                println(t.message)
            }
            override fun onResponse(call: Call<Quiz>, response: Response<Quiz>) {
                val a = View.OnClickListener {
                    isLastLayout = true
                    setContentView(R.layout.activity_quizanswer_1)
                    var lin = findViewById<LinearLayout>(R.id.linearHobbies)
                    var ltInflater = getLayoutInflater();
                    for (hobbie:Hobbie in quiz!!.user.hobbies){
                        var item = ltInflater.inflate(R.layout.item_hobbie, lin, false)
                        item.findViewById<TextView>(R.id.profession).text = hobbie.hobbie
                        lin.addView(item)
                    }
                    Picasso.get()
                        .load(networkservice.BASE_URL+"/images/"+quiz!!.user.avatar)
                        .error(R.mipmap.ic_launcher)
                        .into(findViewById<ImageView>(R.id.imageKent));
                    findViewById<TextView>(R.id.job_title).text = quiz!!.user.profession.profession
                    findViewById<TextView>(R.id.fio_person).text = quiz!!.user.name
                    findViewById<TextView>(R.id.any_facts).text = quiz!!.user.about
                }

                quiz = response.body()!!
                println(quiz)
                println(networkservice.BASE_URL+"/images/"+quiz!!.user.avatar)
                if (quiz!=null){
                    setContentView(R.layout.activity_quiz_1)
                    Picasso.get()
                        .load(networkservice.BASE_URL+"/images/"+quiz!!.user.avatar)
                        .error(R.mipmap.ic_launcher)
                        .into(findViewById<ImageView>(R.id.photo_user));

                    //.placeholder(R.drawable.user_placeholder)
                        //.error(R.drawable.user_placeholder_error)
                        //.into(findViewById(R.id.photo_user));
                    findViewById<TextView>(R.id.quest_1).text = quiz!!.question
                    findViewById<TextView>(R.id.choice_1).text = quiz!!.variants[0].value
                    findViewById<TextView>(R.id.choice_1).setOnClickListener(a)
                    findViewById<TextView>(R.id.choice_2).text = quiz!!.variants[1].value
                    findViewById<TextView>(R.id.choice_2).setOnClickListener(a)
                    findViewById<TextView>(R.id.choice_3).text = quiz!!.variants[2].value
                    findViewById<TextView>(R.id.choice_3).setOnClickListener(a)
                    findViewById<TextView>(R.id.choice_4).text = quiz!!.variants[3].value
                    findViewById<TextView>(R.id.choice_4).setOnClickListener(a)
                }
                else {
                    println("Не удалось получить квиз")
                    //Toast.makeText(QuizActivity().javaClass, "Упс.. Что-то пошло не так", Toast.LENGTH_SHORT).show()
                }            }
        })

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> x1 = event.x
            MotionEvent.ACTION_UP -> {
                x2 = event.x
                val deltaX: Float = x1 - x2
                if (deltaX > MIN_DISTANCE && isLastLayout) {
                    newQuiz(findViewById<TextView>(R.id.text_next))
                }
            }
        }
        return super.onTouchEvent(event)
    }
    fun newQuiz(view: View){
        isLastLayout = false
        if (count > 0 ){
            setQuiz()
        }
        else{
            val intent = Intent(this, EventsActivity().javaClass)
            startActivity(intent)
        }
    }
}