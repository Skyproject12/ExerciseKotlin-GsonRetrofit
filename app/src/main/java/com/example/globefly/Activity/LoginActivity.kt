package com.example.globefly.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.globefly.Api.RetrofitClient
import com.example.globefly.Model.LoginResponse
import com.example.globefly.R
import com.example.globefly.Storage.SharedPrefManager
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            val email = input_email.text.toString().trim()
            val password = input_password.text.toString().trim()

            if (email.isEmpty()) {
                input_email.error = "Email required"
                input_email.requestFocus()
                return@setOnClickListener
            } else if (password.isEmpty()) {
                input_password.error = "Password required"
                input_password.requestFocus()
                return@setOnClickListener
            } else {
                RetrofitClient.instance.userLogin(
                    email,
                    password
                ).enqueue(object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        // ketika response body tidak error maka jalankan fungsi
                        if (!response.body()?.error!!) {
                            // menyimpan data hasil select ke dalam share preference
                            SharedPrefManager.getInstance(applicationContext)
                                .saveUser(response.body()?.user!!)
                            // jika berhasil maka intent ke profil activity
                            val intent = Intent(applicationContext, ProfilActiviy::class.java)
                            intent.flags =
                                Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                            startActivity(intent)
                        } else {

                        }
                    }

                })
            }

        }
    }

    override fun onStart() {
        // jika sudah login maka intent ke profil activity
        super.onStart()
        if(SharedPrefManager.getInstance(this).isLoggedIn){
            val intent= Intent(applicationContext, ProfilActiviy::class.java)
            startActivity(intent)

        }
    }

}
