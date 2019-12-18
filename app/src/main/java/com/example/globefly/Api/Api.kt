package com.example.globefly.Api


import com.example.globefly.Model.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api{
    // get response api
    @FormUrlEncoded
    @POST("createuser")
    fun createUser(
        // give needed for request api
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("name") name: String,
        @Field("school") school: String
    // call name response yang akan digunakan
    ): Call<DefaultResponse>
}
