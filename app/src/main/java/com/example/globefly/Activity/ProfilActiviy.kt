package com.example.globefly.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.globefly.R
import com.example.globefly.Storage.SharedPrefManager

class ProfilActiviy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
    }

    override fun onStart() {
        // mengecek jika  belum login maka kembalikan ke activity login
        super.onStart()
        if(!SharedPrefManager.getInstance(this).isLoggedIn){
            val intent= Intent(applicationContext,LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
