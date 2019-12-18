package com.example.globefly.Storage

import android.content.Context
import com.example.globefly.Model.User

class SharedPrefManager private constructor(private val mCtx: Context) {
    // initialisasi sharefreference
    val isLoggedIn: Boolean
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return sharedPreferences.getInt("id", -1) != -1
        }
    // // get user
    val user: User
        get() {
            val sharedPreferences =
                mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
            return User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("school", null)
            )

        }

    // save user
    fun saveUser(user: User) {
        val sharedPreference = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putInt("id", user.id)
        editor.putString("email", user.email)
        editor.putString("name", user.name)
        editor.putString("school", user.school)
        editor.apply()
    }

    // clear sharePreference
    fun clear() {
        val sharePreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharePreferences.edit()
        editor.clear()
        editor.apply()
    }

    // initialisasi sharefrefmanager
    companion object {
        private val SHARED_PREF_NAME = "my_shared_preff"
        private var mInsatnce: SharedPrefManager? = null
        @Synchronized
        fun getInstance(mCtx: Context): SharedPrefManager {
            if (mInsatnce == null) {
                mInsatnce = SharedPrefManager(mCtx)
            }
            return mInsatnce as SharedPrefManager
        }
    }
}