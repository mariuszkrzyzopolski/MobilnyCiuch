package com.example.mobinyciuch.services

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("user/register")
    fun registerUser(
        @Body userData: RegisterUserData
    ): Call<RegisterResponse>
}

data class RegisterUserData(
    val mail: String,
    val city: String,
    val password: String,
    val repeated_password: String
)

data class RegisterResponse(
    val token: String,
    val expiresIn: String
)