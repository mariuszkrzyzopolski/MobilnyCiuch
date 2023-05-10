package com.example.mobilnyciuch


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.fragment_login, container, false)

        view.findViewById<Button>(R.id.btn_register).setOnClickListener{
         var navRegister = activity as FragmentNawigation
         navRegister.navigateFrag(RegisterFragment(), false)
        }
        return view
    }
}