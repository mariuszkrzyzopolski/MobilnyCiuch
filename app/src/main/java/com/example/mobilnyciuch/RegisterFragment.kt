package com.example.mobilnyciuch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

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
     return view
    }
}