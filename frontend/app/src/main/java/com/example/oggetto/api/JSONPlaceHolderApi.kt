package com.example.oggetto.api

import com.example.oggetto.Model.Hobbie
import com.example.oggetto.Model.Professions
import com.example.oggetto.Model.MyToken
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
}