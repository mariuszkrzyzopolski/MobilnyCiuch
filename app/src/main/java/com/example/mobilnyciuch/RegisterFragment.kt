package com.example.mobilnyciuch

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.RegisterResponse
import com.example.mobinyciuch.services.RegisterUserData
import com.example.mobinyciuch.services.UserService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

      var view = inflater.inflate(R.layout.fragment_register, container, false)
      view.findViewById<Button>(R.id.btn_login_reg).setOnClickListener {
          var navRegister = activity as FragmentNawigation
          navRegister.navigateFrag(LoginFragment(),false)
       }

        view.findViewById<Button>(R.id.btn_register_reg).setOnClickListener {
            val mail = getString(R.string.username)
            val password = getString(R.string.password)
            val repeatedPassword = getString(R.string.confirm_password)

            registerUser(mail, password, repeatedPassword) {
                if (it?.token != null) {
                    // it = newly added user parsed as response
                    // it?.id = newly added user ID
                } else {
                    //Log("Error registering new user")
                }
            }
        }
     return view
    }

    private fun registerUser(mail: String, password: String, repeatedPassword: String, onResult: (RegisterResponse?) -> Unit) {
        val registerRequest = RegisterUserData(mail, "", password, repeatedPassword)
        val userAPI = RetrofitHelper.getInstance().create(UserService::class.java)

        userAPI.registerUser(registerRequest).enqueue(
            object : Callback<RegisterResponse> {
                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    val addedUser = response.body()
                    onResult(addedUser)
                }
            }
        )
    }
}