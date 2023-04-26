package com.example.setsiscase.util

import android.content.Context
import android.content.SharedPreferences
import com.example.setsiscase.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

/**
 * Session manager to save and fetch data from SharedPreferences
 */
class SessionManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),
        Context.MODE_PRIVATE)

    companion object{
        const val ACCESS_TOKEN= "access_token"
    }

    /**
     * Function to save auth token
     */
    fun saveToken(token: String) {
        val editor=prefs.edit()
        editor.putString(ACCESS_TOKEN, token)
        editor.apply()

    }

    /**
     * Function to fetch auth token
     */
    fun getToken(): String? {
        return prefs.getString(ACCESS_TOKEN,null)
    }
}