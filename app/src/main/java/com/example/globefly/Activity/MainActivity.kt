package com.example.globefly.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.globefly.Api.RetrofitClient
import com.example.globefly.Model.DefaultResponse
import com.example.globefly.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // make onclick
        button_signup.setOnClickListener {
            // mengambil data lalu mengubah menjadi string
            val name = input_name.text.toString().trim()
            val password = input_password.text.toString().trim()
            val school = input_school.text.toString().trim()
            val email = input_email.text.toString().trim()
            // set edittext ketika error
            if (name.isEmpty()) {
                input_name.error = "Name required"
                // ketika edittext kosong
                input_name.requestFocus()
                return@setOnClickListener
            }
            else if (password.isEmpty()) {
                input_password.error = "Password required"
                input_password.requestFocus()
                return@setOnClickListener
            }
            else if (school.isEmpty()) {
                input_school.error = "School required"
                input_school.requestFocus()
                return@setOnClickListener
            }
            else if (email.isEmpty()) {
                input_email.error = "Email required"
                input_email.requestFocus()
                return@setOnClickListener
            }
            RetrofitClient.instance.createUser(
                email, password, name, school
            ).enqueue(object : Callback<DefaultResponse> {
                // ketika response gagal
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                // ketika response berhasil
                override fun onResponse(
                    call: Call<DefaultResponse>,
                    response: Response<DefaultResponse>
                ) {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}
