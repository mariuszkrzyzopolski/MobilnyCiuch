package com.example.mobilnyciuch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.UserService

/**
 * Fragment for main navigation, where user can choose where to go
 */
class MenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_menu, container, false)
        val userService = RetrofitHelper.getInstance().create(UserService::class.java)
        view.findViewById<Button>(R.id.button_logout).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(LoginFragment(userService), false)
        }

        view.findViewById<Button>(R.id.button_collectionPreview).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(CollectionFragment(), false)
        }

        view.findViewById<Button>(R.id.button_addClothes).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(AddToCollectionFragment(), false)
        }

        view.findViewById<Button>(R.id.button_setPreview).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(SetPreviewFragment(), false)
        }

        view.findViewById<Button>(R.id.button_creatingSets).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(AddSetFragment(), false)
        }

        return view
    }
}