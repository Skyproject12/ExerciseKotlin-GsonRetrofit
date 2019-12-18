package com.example.globefly.Api

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient{
    // defination auth in header AUTHORIZATION
    private val AUTH= "Basic"+Base64.encodeToString("belalkhan:123456".toByteArray(), Base64.NO_WRAP)
    // give base url
    private const val BASE_URL= "http://localhost/myapi/public/"
    private val okHttpClient= OkHttpClient.Builder().addInterceptor{ chain ->
        val original = chain.request()
        val requestBuilder= original.newBuilder()
            .addHeader("Authorization", AUTH)
            .method(original.method(), original.body())
        val request= requestBuilder.build()
        chain.proceed(request)
    }.build()
    // defination type variable is interface Api
    val instance: Api by lazy{
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        // call interface api
        retrofit.create(Api::class.java)
    }
}