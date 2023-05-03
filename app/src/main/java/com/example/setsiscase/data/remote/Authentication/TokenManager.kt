package com.example.setsiscase.data.remote.Authentication

import android.content.Context
import android.content.SharedPreferences
import com.example.setsiscase.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)

    fun saveToken(tokenAccess: String,tokenRefresh: String) {
        val editor=prefs.edit()
        editor.putString(ACCESS_TOKEN, tokenAccess)
        editor.putString(REFRESH_TOKEN, tokenRefresh)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }

    fun getRefreshToken(): String? {
        return prefs.getString(REFRESH_TOKEN, null)
    }


    fun clearSharedPref(){
        val editor = prefs.edit()
        editor.clear().apply()
    }

    companion object{
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"

    }
}