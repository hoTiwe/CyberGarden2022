package com.example.oggetto.api

import com.example.oggetto.Model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*


interface JSONPlaceHolderApi {
    @GET("shared/hobbies")
    fun getHobbies(): Call<List<Hobbie>>?

    @GET("shared/professions")
    fun getProfessions(): Call<List<Professions>>?

    @Multipart
    @POST("auth/register")
    fun registration(
        @Part("email") email: String,
        @Part avatar: MultipartBody.Part,
        @Part("password") password: String,
        @Part("name") name: String,
        @Part("profession") professions: Int,
        @Part("about") about: String,
        @Part("link_inst") link_inst: String,
        @Part("link_tg") link_tg: String,
        @Part("link_vk") link_vk: String,
        @Part("hobbies") hobbies: List<Int>
    ) : Call<MyToken>

    @GET("quiz/profession")
    fun firstQuiz(
        @Header("Authorization") token: String
    ): Call<Quiz>

    @GET("users/user")
    fun firstUser(
        @Header("Authorization") token: String
    ): Call<UserQuiz>

    @GET("events")
    fun getEventsList(
        @Header("Authorization") token: String
    ): Call<List<Event>>
}