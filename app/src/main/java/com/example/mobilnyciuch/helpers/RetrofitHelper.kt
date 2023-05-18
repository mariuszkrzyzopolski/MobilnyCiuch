package com.example.mobilnyciuch.helpers

import okhttp3.Authenticator
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object RetrofitHelper {
    private val client = OkHttpClient.Builder().authenticator(object : Authenticator {
        @Throws(IOException::class)
        override fun authenticate(route: Route?, response: Response): Request? {
            if (response.request.header("Authorization") != null) {
                return null // Give up, we've already attempted to authenticate.
            }

            println("Authenticating for response: $response")
            println("Challenges: ${response.challenges()}")
            val credential = Credentials.basic("test@test.com", "password")
            return response.request.newBuilder()
                .header("Authorization", credential)
                .build()
        }
    }).build()
    val baseUrl = "http://10.0.2.2:8000/" // bridge to localhost

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
    }
}