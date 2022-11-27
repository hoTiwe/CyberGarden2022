package com.example.oggetto.api

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import com.example.oggetto.Model.Session
import com.example.oggetto.QuizActivity
import com.example.oggetto.RegistrationActivity
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class HeaderInterceptor : Interceptor {
//    var session = Session(RegistrationActivity())
//    var token = session.getSession()
//    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
//        proceed(
//            request()
//                .newBuilder()
//                .addHeader("Authorization", token!!)
//                .build()
//        )
//    }
//}

object  networkservice {
    private var mInstance: networkservice? = null
    public val BASE_URL = "https://0109-2a00-1fa1-4199-993c-19a6-cedb-48a8-25c2.eu.ngrok.io" //адрес сервера

    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}