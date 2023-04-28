package com.example.setsiscase.util

import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.setsiscase.R
import com.example.setsiscase.data.remote.dto.Token
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class SessionManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs: SharedPreferences = context.getSharedPreferences(
        context.getString(R.string.app_name),
        Context.MODE_PRIVATE
    )

    companion object {
        const val ACCESSTOKEN = "accessToken"

    }

    /**
     * Function to save auth token
     */
    fun saveToken(token: String) {
        val editor = prefs.edit()
        token.let {
            editor.putString(ACCESSTOKEN, token)
            editor.apply()
        }
    }

    fun deleteToken() {
        val editor = prefs.edit()
        editor.remove(ACCESSTOKEN)
        editor.apply()
    }

    /**
     * Function to fetch auth token
     */
    fun getToken(): String? {
        return prefs.getString(ACCESSTOKEN, null)
    }
}

