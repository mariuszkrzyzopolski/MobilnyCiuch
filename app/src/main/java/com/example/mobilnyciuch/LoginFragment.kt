package com.example.mobilnyciuch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.LoginResponse
import com.example.mobinyciuch.services.LoginUserData
import com.example.mobinyciuch.services.RegisterResponse
import com.example.mobinyciuch.services.RegisterUserData
import com.example.mobinyciuch.services.UserService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment(private val userService: UserService) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.btn_register).setOnClickListener{
         var navRegister = activity as FragmentNawigation
         navRegister.navigateFrag(RegisterFragment(), false)
        }

        view.findViewById<Button>(R.id.btn_login).setOnClickListener {
            val loginEditText = view.findViewById<TextInputEditText>(R.id.loginEditText)
            val login = loginEditText.text.toString()

            val passwordEditText = view.findViewById<TextInputEditText>(R.id.passwordEditText)
            val password = passwordEditText.text.toString()

            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setMessage("Logowanie...")
                .setCancelable(false)
                .create()

            dialog.show()

            login(login, password) {
                dialog.dismiss()
                if (it?.token != null) {
                    // TODO: save token
                    var navRegister = activity as FragmentNawigation
                    navRegister.navigateFrag(MenuFragment(),false)
                } else {
                    val dialog = MaterialAlertDialogBuilder(requireContext())
                        .setMessage("Nie można zalogować użytkownika")
                        .setPositiveButton("OK") { dialog, which ->
                            dialog.dismiss()
                        }
                        .create()
                    dialog.show()
                }
            }
        }
        return view
    }

    fun login(mail: String, password: String, onResult: (LoginResponse?) -> Unit) {
        val loginRequest = LoginUserData(mail, password)
        val userAPI = userService

        userAPI.login(loginRequest).enqueue(
            object : Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Handle successful response
                        val result = response.body()
                        onResult(result)
                    } else if (response.code() == 400) {
                        // Handle 400 Bad Request error
                        onResult(null)
                    } else {
                        // Handle other error codes
                        onResult(null)
                    }
                }
            }
        )
    }
}