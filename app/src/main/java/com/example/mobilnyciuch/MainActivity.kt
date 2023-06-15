package com.example.mobilnyciuch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mobilnyciuch.helpers.RetrofitHelper
import com.example.mobinyciuch.services.UserService

/**
 * Activity responsible for navigation between fragments and navigate to login fragment
 */
class MainActivity : AppCompatActivity(), FragmentNawigation{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userService = RetrofitHelper.getInstance().create(UserService::class.java)
        supportFragmentManager.beginTransaction().add(R.id.container,LoginFragment(userService)).commit()
    }

    override fun navigateFrag(fragment: Fragment, addToStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction().replace(R.id.container,fragment)
        if(addToStack){
            transaction.addToBackStack(null)
        }
        transaction.commit()
    }
}