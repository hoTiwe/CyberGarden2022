package com.example.oggetto

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.oggetto.Model.*
import com.example.oggetto.adapter.GridAdapter
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.util.*


class RegistrationActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val user = User(null, null,null,null,null,null, listOf(),null,null,null)
    var pickedPhoto : Uri? = null
    var imageFile: File? = null
    var context: Context? = null
    var professions: List<Professions> = listOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration_1)
        context = applicationContext;
    }

    fun pickPhoto(view: View){
        if (ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) { // izin alınmadıysa
            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1)
        } else {
            val galeriIntext = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galeriIntext,2)
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.size > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                val galeriIntext = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(galeriIntext,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {
            pickedPhoto = data.data
            if (pickedPhoto != null) {
                //val uriPathHelper = URIPathHelper()
                //val filePath = uriPathHelper.getPath(RegistrationActivity(), pickedPhoto!!)
                if (pickedPhoto != null) {
                    imageFile = getPath(pickedPhoto)?.let { File(it) }

                    println(pickedPhoto!!.toString())
                }
            }
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun getPath(uri: Uri?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor: Cursor = managedQuery(uri, projection, null, null, null)
        startManagingCursor(cursor)
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    private fun setError(message: String){
        val layot = findViewById<ConstraintLayout>(R.id.incorrect_data)
        layot.visibility = View.VISIBLE
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

            Common.retrofitService.getProfessions()!!.enqueue(object : Callback<List<Professions>> {
                override fun onFailure(call: Call<List<Professions>>, t: Throwable) {
                    println(t.message)
                }
                override fun onResponse(call: Call<List<Professions>>, response: Response<List<Professions>>) {
                    professions = response.body()!!
                    println(professions)
                    var arrayProfessions = List<String>(professions!!.size) { i -> professions[i].profession }
                    var spinner = findViewById<Spinner>(R.id.spinner)
                    println(arrayProfessions)
                    var adapter = ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, arrayProfessions)
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                    spinner.adapter = adapter
                    spinner.onItemSelectedListener = this@RegistrationActivity
                }
            })

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, v: View?, position: Int, id: Long) {
        user.profession = professions[position].id
    }

    override fun onNothingSelected(p0: AdapterView<*>?){}


    private fun checkName(name:String):String?{
        return if (name.isNotEmpty()){
            val words = name.split(" ")
            var actualName = ""
            try {
                for (word: String in words) {
                    actualName += word[0].uppercaseChar() + word.substring(1)
                        .lowercase(Locale.ROOT) + " "
                }
            }
            catch (e: Exception){
                actualName = name
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

        if (wordsAboutSelf.isEmpty()){ setError("Введите информацию о себе") }
        else {
            if (checkName(name) == null) {
                setError("Введите ФИО")
            }
            else{
                if (user.profession==null){ setError("Выберите профессию") }
                else{
                    user.name = name
                    user.wordAboutSelf = wordsAboutSelf
                    setContentView(R.layout.activity_registration_3)
                    Common.retrofitService.getHobbies()!!.enqueue(object : Callback<List<Hobbie>> {
                        override fun onFailure(call: Call<List<Hobbie>>, t: Throwable) {
                            println(t.message)
                        }
                        private var mAdapter: GridAdapter? = null

                        override fun onResponse(call: Call<List<Hobbie>>, response: Response<List<Hobbie>>) {
                            var hobbies = response.body() as List<Hobbie>
                            mAdapter = GridAdapter(
                                applicationContext,
                                android.R.layout.simple_list_item_1,
                                hobbies
                            )
                            var g =  findViewById<GridView>(R.id.matrix);

                            g.setAdapter(mAdapter)
                            g.setOnItemClickListener(OnItemClickListener { parent, v, position, id -> // TODO Auto-generated method stub
                                var govno = mAdapter!!.getItem(position)
                                if (govno.id in user.hobbies){
                                    user.hobbies -= govno.id
                                    v.setBackgroundColor(getColor(R.color.white))
                                }
                                else{
                                    user.hobbies += govno.id
                                    v.setBackgroundColor(getColor(R.color.yellow))
                                }
                            })
                        }
                    })
                }
            }
        }
    }




    fun invisibleSelectHobbies(view: View){
        var constr = findViewById<ConstraintLayout>(R.id.selectedDialog)
        constr.visibility = View.GONE
    }
    fun selectHobbies(vies: View){
        var constr = findViewById<ConstraintLayout>(R.id.selectedDialog)
        constr.visibility = View.VISIBLE

    }
    fun goFragment4(view: View){
        if (imageFile!=null && user.hobbies.isNotEmpty()){
            user.image = imageFile
            setContentView(R.layout.activity_registration_4)
        }
        else {
            if (imageFile == null) {
                setError("Установите фото")
            }
            else setError("Выбирете хобби")
        }
    }
    fun openQuiz(view: View){
        val intent = Intent(this, QuizActivity().javaClass)
        startActivity(intent)
    }
    fun endRegistration(view: View){
        user.link_inst = findViewById<EditText>(R.id.input_inst).text.toString()
        user.link_vk = findViewById<EditText>(R.id.input_vk).text.toString()
        user.link_tg = findViewById<EditText>(R.id.inputLinkTelegram).text.toString()
        var requestFile =
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), user.image!!)
        println(user.image!!.name + user.image!!.extension)
        val multiPartBody =
            MultipartBody.Part.createFormData("avatar", user.image!!.name, requestFile)
        try {
            Common.retrofitService.registration(
                email = user.email!!,
                password = user.password!!,
                name = user.name!!,
                professions = user.profession!!,
                about = user.wordAboutSelf!!,
                link_inst = user.link_inst!!,
                link_tg = user.link_tg!!,
                link_vk = user.link_vk!!,
                hobbies = user.hobbies!!,
                avatar = multiPartBody
            ).enqueue(object : Callback<MyToken> {
                override fun onFailure(token: Call<MyToken>, t: Throwable) {
                    println(t.message)
                }

                override fun onResponse(call: Call<MyToken>, response: Response<MyToken>) {
                    var token: String? = response.body()!!.token
                    if (token != null) {
                        var session = Session(context)
                        session.setSession(token)
                        println("$token")
                        setContentView(R.layout.activity_greetings)

                    }
                }
            })
        }
        catch (e: Exception){
            Toast.makeText(this, "Упс.. Что-то пошло не так", Toast.LENGTH_SHORT).show()
        }

    }
    fun goBack(view: View){ onBackPressed() }
}




