package com.example.oggetto.api

import com.example.oggetto.Model.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*
import java.io.FileDescriptor


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

    @Multipart
    @POST("events")
    fun makeEvent(
        @Header("Authorization") token: String,
        @Part("title") title: String,
        @Part("chat") chat: String,
        @Part("adress") adress: String?,
        @Part("start") start: String,
        @Part("end") end: String?,
        @Part("description") descriptor: String?
    ) : Call<Void>

    @GET("events/id")
    fun getEvent(
        @Query("id") id: String
    ): Call<Event>
}