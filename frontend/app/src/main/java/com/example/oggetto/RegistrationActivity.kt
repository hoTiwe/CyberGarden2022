package com.example.oggetto

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.oggetto.Model.User
import java.util.*

class RegistrationActivity : AppCompatActivity() {
    val user = User(null, null,null,null,null,null, mutableSetOf(),null,null,null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_1)
    }

    private fun setError(message: String){
        val layot = findViewById<ConstraintLayout>(R.id.incorrect_data)
        layot.visibility = View.VISIBLE
        println(layot.visibility)
        println(message)
        val text = findViewById<TextView>(R.id.textError)
        text.text = message
    }


    private fun checkPassword(firstPassword: String, secondPassword: String):Boolean{
        if (firstPassword != secondPassword){
            setError("Пароли не совпадают")
            return false
        }
        if (firstPassword.length < 6){
            setError("Пароль должен быть не короче 6 символов")
            return false
        }
        return true
    }

    private fun checkEmail(email: String): Boolean{
        if ("@oggettoweb.com" !in email){
            println("Error email")
            setError("email должен быть в зоне @oggettoweb.com")
            return false
        }
        if (email.length<16){
            setError("Некорректная почта")
            return false
        }
        return true
    }
    fun goBackFragment1(view: View){
        setContentView(R.layout.activity_registration_1)
    }
    fun goBackFragment2(view: View){
        setContentView(R.layout.activity_registration_2)
    }
    fun goBackFragment3(view: View){
        setContentView(R.layout.activity_registration_3)
    }
    fun goFragment2(view: View){
        val email = findViewById<EditText>(R.id.inputEmail).text.toString()
        val firstPassword = findViewById<EditText>(R.id.input_password).text.toString()
        val secondPassword = findViewById<EditText>(R.id.input_repeat_password).text.toString()
        if (checkEmail(email) && checkPassword(firstPassword, secondPassword)){
            user.email = email
            user.password = firstPassword
            setContentView(R.layout.activity_registration_2)
        }
    }

    private fun checkName(name:String):String?{
        return if (name.isNotEmpty()){
            val words = name.split(" ")
            var actualName = ""
            for (word: String in words){
                actualName += word[0].uppercaseChar() + word.substring(1).lowercase(Locale.ROOT) + " "
            }
            println(actualName)
            return actualName
        }
        else{
            setError("Ввежите имя")
            null
        }
    }

    fun goFragment3(view: View){
        val name = findViewById<EditText>(R.id.inputName).text.toString()
        val wordsAboutSelf = findViewById<EditText>(R.id.wordAboutSelf).text.toString()

        // add profession
        if (wordsAboutSelf.isEmpty()) setError("Введите информацию о себе")
        else{
            if (checkName(name)!=null)setContentView(R.layout.activity_registration_3)
        }
    }
    fun goBack(view: View){ onBackPressed() }
}