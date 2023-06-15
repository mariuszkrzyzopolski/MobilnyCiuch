package com.example.mobilnyciuch

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.example.mobinyciuch.services.ItemCollectionServiceImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.Mockito.mock

class SetPreviewFragmentTest {

    @Test
    fun testUpdateUIWithSets() {
        val scenario = launchFragmentInContainer<SetPreviewFragment>(
            themeResId = R.style.Theme_MobilnyCiuch
        )
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onFragment { fragment ->
            val sets = listOf(
                ItemCollectionServiceImpl.Set(1, listOf(R.drawable.foot1, R.drawable.low1, R.drawable.up1)),
                ItemCollectionServiceImpl.Set(2, listOf(R.drawable.foot2, R.drawable.low2, R.drawable.up2))
            )
            val rootView = LinearLayout(fragment.requireContext())
            val linearLayout = LinearLayout(fragment.requireContext())

            rootView.addView(linearLayout) // Add linearLayout to rootView

            fragment.updateUIWithSets(sets, rootView)

            assertTrue(linearLayout.childCount > 0)
            assertEquals(sets.size, linearLayout.childCount)

            val cardView = linearLayout.getChildAt(0) as CardView
            val setText = cardView.findViewById<TextView>(R.id.setText)

            assertEquals("Zestaw nr 1", setText.text)
        }
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }
}
