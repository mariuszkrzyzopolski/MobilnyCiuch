package com.example.mobilnyciuch

import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.core.app.ApplicationProvider
import com.example.mobinyciuch.services.ItemCollectionService
import com.example.mobinyciuch.services.ItemCollectionServiceImpl
import com.example.mobinyciuch.services.ItemCollectionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4



@RunWith(AndroidJUnit4::class)

    class SetPreviewFragmentTest {

    private lateinit var fragment: SetPreviewFragment

    @Mock
    private lateinit var itemCollectionService: ItemCollectionService

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        val viewModel = ItemCollectionViewModel(itemCollectionService)
        fragment = SetPreviewFragment(viewModel)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `updateUIWithSets shows empty message when sets list is empty`() {
        // Arrange
        val context = ApplicationProvider.getApplicationContext<Context>()
        val linearLayout = LinearLayout(context)
        val sets = emptyList<ItemCollectionServiceImpl.Set>()

        // Act
        fragment.updateUIWithSets(sets, fragment.requireView())

        // Assert
        val childLinearLayout = linearLayout.getChildAt(0) as LinearLayout
        assert(linearLayout.childCount == 1)
        assert(childLinearLayout.childCount == 1)
        assert(childLinearLayout.getChildAt(0) is TextView)
        assert(childLinearLayout.getChildAt(0).layoutParams is LinearLayout.LayoutParams)
        assert(childLinearLayout.getChildAt(0).layoutParams.width == LinearLayout.LayoutParams.MATCH_PARENT)
        assert(childLinearLayout.getChildAt(0).layoutParams.height == LinearLayout.LayoutParams.MATCH_PARENT)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `updateUIWithSets adds CardView for each set in sets list`() {
        // Arrange
        val linearLayout = LinearLayout(ApplicationProvider.getApplicationContext())
        val sets = listOf(
            ItemCollectionServiceImpl.Set(1, listOf(
                R.drawable.foot1,
                R.drawable.low1,
                R.drawable.up1
            )),
            ItemCollectionServiceImpl.Set(2, listOf(
                R.drawable.foot1,
                R.drawable.low2,
                R.drawable.up2
            ))
        )

        // Act
        fragment.updateUIWithSets(sets, fragment.requireView())

        // Assert
        assert(linearLayout.childCount == sets.size)
        for (i in 0 until sets.size) {
            val child = linearLayout.getChildAt(i)
            assert(child is CardView)
            assert((child as CardView).childCount == 5)
        }
    }
}
