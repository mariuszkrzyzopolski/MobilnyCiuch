package com.example.mobilnyciuch
import org.junit.runner.RunWith;
import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobinyciuch.services.ItemCollectionService
import com.example.mobinyciuch.services.ItemCollectionServiceImpl
import com.example.mobinyciuch.services.ItemCollectionViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


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
    fun updateUIWithSets_ShowsEmptyMessageWhenSetsListIsEmpty() {
        // Arrange
        val context = ApplicationProvider.getApplicationContext<Context>()
        val linearLayout = LinearLayout(context)
        val sets = emptyList<ItemCollectionServiceImpl.Set>()

        // Act
        fragment.updateUIWithSets(sets, fragment.requireView())

        // Assert
        assert(linearLayout.childCount == 1)
        val childLinearLayout = linearLayout.getChildAt(0) as LinearLayout
        assert(childLinearLayout.childCount == 1)
        assert(childLinearLayout.getChildAt(0) is TextView)
        assert(childLinearLayout.getChildAt(0).layoutParams.width == LinearLayout.LayoutParams.MATCH_PARENT)
        assert(childLinearLayout.getChildAt(0).layoutParams.height == LinearLayout.LayoutParams.MATCH_PARENT)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun updateUIWithSets_AddsCardViewForEachSetInSetsList() {
        // Arrange
        val linearLayout = LinearLayout(ApplicationProvider.getApplicationContext())
        val sets = listOf(
            ItemCollectionServiceImpl.Set(1, emptyList()),
            ItemCollectionServiceImpl.Set(2, emptyList())
        )

        // Act
        fragment.updateUIWithSets(sets, fragment.requireView())

        // Assert
        assert(linearLayout.childCount == sets.size)
        for (i in 0 until sets.size) {
            val child = linearLayout.getChildAt(i)
            assert(child is CardView)
            assert((child as CardView).childCount == 0) // Assuming the CardView does not have any children in this test
        }
    }
}
