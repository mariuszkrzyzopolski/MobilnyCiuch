package com.example.mobilnyciuch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.RegisterResponse
import com.example.mobinyciuch.services.RegisterUserData
import com.example.mobinyciuch.services.UserService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {
    private var _binding: FragmentNawigation?= null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      var view = inflater.inflate(R.layout.fragment_register, container, false)

      view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
          navigateToLoginPage()
       }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            val loginEditText = view.findViewById<TextInputEditText>(R.id.loginEditText)
            val login = loginEditText.text.toString()

            val passwordEditText = view.findViewById<TextInputEditText>(R.id.passwordEditText)
            val password = passwordEditText.text.toString()

            val confirmPasswordEditText = view.findViewById<TextInputEditText>(R.id.confirmPasswordEditText)
            val confirmPassword = confirmPasswordEditText.text.toString()

            val dialog = MaterialAlertDialogBuilder(requireContext())
                .setMessage("Rejestrowanie nowego użytkownika...")
                .setCancelable(false)
                .create()

            dialog.show()

            registerUser(login, password, confirmPassword) {
                dialog.dismiss()
                val successMessage = "Konto zostało założone poprawnie"
                val errorMessage = "Nastąpił błąd podczas rejestracji konta"
                val message = if (it?.token != null) successMessage else errorMessage
                val dialog = MaterialAlertDialogBuilder(requireContext())
                    .setMessage(message)
                    .setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                        if (it?.token != null) navigateToLoginPage()
                    }
                    .create()
                dialog.show()
            }
        }
     return view
    }

    private fun registerUser(mail: String, password: String, repeatedPassword: String, onResult: (RegisterResponse?) -> Unit) {
        val registerRequest = RegisterUserData(mail, "city", password, repeatedPassword)
        val userAPI = RetrofitHelper.getInstance().create(UserService::class.java)

        userAPI.registerUser(registerRequest).enqueue(
            object : Callback<RegisterResponse> {
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
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

    private fun navigateToLoginPage() {
        var navRegister = activity as FragmentNawigation
        navRegister.navigateFrag(LoginFragment(),false)
    }
}