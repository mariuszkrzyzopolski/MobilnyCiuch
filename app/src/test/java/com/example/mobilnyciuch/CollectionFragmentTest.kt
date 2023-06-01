package com.example.mobilnyciuch

import android.view.View
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.Test


internal class CollectionFragmentTest {

    @Test
    fun onCreateView() {
//        with(launchFragment<CollectionFragment>()) {
//            onFragment { fragment ->
//                fragment.view?.findViewById<View>(R.id.button_collection)
//                org.junit.Assert.assertEquals(fragment.view?.findViewById<View>(R.id.button_collection)?.visibility, View.VISIBLE)
////                org.junit.Assert.assertEquals(fragment.view?.findViewById<View>(R.id.carousel_upper).d, 4)
//            }
//        }
        val scenario = launchFragmentInContainer<CollectionFragment>()
        scenario.onFragment{ fragment ->
            org.junit.Assert.assertEquals(fragment.view?.findViewById<View>(R.id.button_collection)?.visibility, View.VISIBLE)
        }

    }
}