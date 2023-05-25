package com.example.mobilnyciuch

import com.example.mobinyciuch.services.LoginResponse
import com.example.mobinyciuch.services.LoginUserData
import com.example.mobinyciuch.services.UserService
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragmentTest {

    @Mock
    private lateinit var call: Call<LoginResponse>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }
    @Test
    fun login_SuccessfulResponse_SaveToken() {
        // Create an instance of LoginFragment
        val userService = Mockito.mock(UserService::class.java)
        val fragment = LoginFragment(userService)

        // Mock the login response
        val loginResponse = LoginResponse(token = "testToken")
        val loginRequest = LoginUserData("test@example.com", "testPassword")
        Mockito.`when`(userService.login(loginRequest)).thenReturn(call)
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<LoginResponse>
            callback.onResponse(call, Response.success(loginResponse))
        }

        // Call the login function with mocked data
        var savedToken: String? = null
        fragment.login("test@example.com", "testPassword") {
            savedToken = it?.token
        }

        // Verify that the token was saved
        org.junit.Assert.assertEquals("testToken", savedToken)
    }

    @Test
    fun login_FailureResponse_NullToken() {
        val userService = Mockito.mock(UserService::class.java)
        val fragment = LoginFragment(userService)

        // Mock the failure response
        val loginRequest = LoginUserData("test@example.com", "testPassword")
        Mockito.`when`(userService.login(loginRequest)).thenReturn(call)
        Mockito.`when`(call.enqueue(Mockito.any())).thenAnswer {
            val callback = it.arguments[0] as Callback<LoginResponse>
            callback.onFailure(call, Throwable())
        }

        // Call the login function with mocked data
        var savedToken: String? = "previousToken"
        fragment.login("test@example.com", "testPassword") {
            savedToken = it?.token
        }

        // Verify that the token was not changed
        org.junit.Assert.assertNull(savedToken)
    }
}