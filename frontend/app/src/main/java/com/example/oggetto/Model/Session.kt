package com.example.oggetto.Model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager


class Session {

    private var prefs // SharedPreferences для менеджера
            : SharedPreferences? = null
    constructor(cntx: Context?) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx)
    }

    // Сеттер сессии юзера
    fun setSession(token: String?) {
        prefs!!.edit().putString("sessionID", token).commit()
    }

    // Удаление сессии
    fun deleteSession() {
        prefs!!.edit().remove("sessionID").commit()
    }

    // Получения сессии (куки)
    fun getSession(): String? {
        val id = prefs!!.getString("sessionID", "")
        println('"'.toString() + id + '"')
        return if (id === "{}" || id == null || id === "") null else id
    }

}