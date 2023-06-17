package com.example.mobilnyciuch

import androidx.fragment.app.Fragment

interface FragmentNawigation {
    fun navigateFrag(fragment: Fragment, addToStack: Boolean)
}
