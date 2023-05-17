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

    @POST("user/login")
    fun login(
        @Body body: LoginUserData
    ): Call<LoginResponse>
}
data class LoginUserData(
    val mail: String,
    val password: String,
)

data class LoginResponse (
    val token: String
)

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