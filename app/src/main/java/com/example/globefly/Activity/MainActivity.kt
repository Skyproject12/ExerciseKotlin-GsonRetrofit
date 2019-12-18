package com.example.globefly.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.globefly.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // make onclick
        button_signup.setOnClickListener{
            val name= input_name.text.toString().trim()
            val password= input_password.text.toString().trim()
            val school= input_school.text.toString().trim()
            val email= input_email.text.toString().trim()

        }
    }
}
