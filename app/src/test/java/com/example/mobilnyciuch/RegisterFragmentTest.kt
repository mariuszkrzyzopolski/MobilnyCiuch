package com.example.mobilnyciuch

import com.example.mobinyciuch.services.LoginResponse
import com.example.mobinyciuch.services.LoginUserData
import com.example.mobinyciuch.services.RegisterResponse
import com.example.mobinyciuch.services.RegisterUserData
import com.example.mobinyciuch.services.UserService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragmentTest {

    @Mock
    private lateinit var call: Call<RegisterResponse>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun register_SuccessfulResponse_SaveToken() {
        // Create an instance of LoginFragment
        val userService = Mockito.mock(UserService::class.java)
        val fragment = RegisterFragment(userService)

        // Mock the register response
        val registerResponse = RegisterResponse(token = "testToken", expiresIn = "test")
        val registerRequest = RegisterUserData("test@example.com", "city", "testPassword", "testPassword")
        Mockito.`when`(userService.registerUser(registerRequest)).thenReturn(call)
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<RegisterResponse>
            callback.onResponse(call, Response.success(registerResponse))
        }

        // Call the register function with mocked data
        var savedToken: String? = null
        fragment.registerUser("test@example.com", "testPassword", "testPassword") {
            savedToken = it?.token
        }

        // Verify that the token was saved
        org.junit.Assert.assertEquals("testToken", savedToken)
    }

    @Test
    fun register_FailureResponse_NullToken() {
        val userService = Mockito.mock(UserService::class.java)
        val fragment = RegisterFragment(userService)

        // Mock the failure response
        val registerRequest = RegisterUserData("test@example.com", "city", "testPassword", "testPassword1")
        Mockito.`when`(userService.registerUser(registerRequest)).thenReturn(call)
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<RegisterResponse>
            callback.onFailure(call, Throwable())
        }

        // Call the login function with mocked data
        var savedToken: String? = "previousToken"
        fragment.registerUser("test@example.com", "testPassword", "testPassword1") {
            savedToken = it?.token
        }

        // Verify that the token was not changed
        org.junit.Assert.assertNull(savedToken)
    }
}