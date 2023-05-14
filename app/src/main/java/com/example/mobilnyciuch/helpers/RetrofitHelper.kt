package com.example.mobilnyciuch.helpers

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private val client = OkHttpClient.Builder().build()
    val baseUrl = "https://localhost:8000/" // bridge to localhost

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}