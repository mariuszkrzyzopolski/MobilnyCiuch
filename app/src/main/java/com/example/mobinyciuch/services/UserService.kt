package com.example.mobinyciuch.services

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * UserService - interface for user service
 */
interface UserService {

    /**
     * registerUser - function for register user
     * @param userData RegisterUserData
     * @return Call<RegisterResponse>
     */
    @POST("user/register")
    fun registerUser(
        @Body userData: RegisterUserData
    ): Call<RegisterResponse>

    /**
     * login - function for login user
     * @param body LoginUserData
     * @return Call<LoginResponse>
     */
    @POST("user/login")
    fun login(
        @Body body: LoginUserData
    ): Call<LoginResponse>
}

/**
 * LoginUserData - data class for login user
 * @property mail String
 * @property password String
 */
data class LoginUserData(
    val mail: String,
    val password: String,
)

/**
 * LoginResponse - data class for login response
 * @property token bearer token
 */
data class LoginResponse (
    val token: String
)

/**
 * RegisterUserData - data class for register user
 * @property mail String
 * @property city String
 * @property password String
 * @property repeated_password String
 */
data class RegisterUserData(
    val mail: String,
    val city: String,
    val password: String,
    val repeated_password: String
)

/**
 * RegisterResponse - data class for register response
 * @property token bearer token
 * @property expiresIn String
 */
data class RegisterResponse(
    val token: String,
    val expiresIn: String
)