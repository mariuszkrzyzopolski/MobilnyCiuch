package com.example.mobilnyciuch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.UserService

class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_menu, container, false)
        val userService = RetrofitHelper.getInstance().create(UserService::class.java)
        view.findViewById<Button>(R.id.button_logout).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(LoginFragment(userService), false)
        }

        view.findViewById<Button>(R.id.button_collectionPreview).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(CollectionFragment(), false)
        }

        view.findViewById<Button>(R.id.button_addClothes).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(AddToCollectionFragment(), false)
        }

        return view
    }
}