package com.example.mobilnyciuch

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mobinyciuch.services.Collection
import com.example.mobinyciuch.services.CollectionViewModel
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockkConstructor
import io.mockk.unmockkAll
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollectionFragmentTest {

    @MockK
    private lateinit var collectionViewModel: CollectionViewModel

    @InjectMockKs
    private lateinit var fragment: CollectionFragment

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testFragmentInitialization() {
        // Mock the behavior of the CollectionViewModel
        val mockedCollection = Collection(
            mutableListOf(
                CarouselItem(imageDrawable = R.drawable.up1),
                CarouselItem(imageDrawable = R.drawable.up2)
            ),
            mutableListOf(
                CarouselItem(imageDrawable = R.drawable.low1),
                CarouselItem(imageDrawable = R.drawable.low2)
            ),
            mutableListOf(
                CarouselItem(imageDrawable = R.drawable.foot1),
                CarouselItem(imageDrawable = R.drawable.foot2)
            )
        )
        mockkConstructor(CollectionViewModel::class)
        every { anyConstructed<CollectionViewModel>().getCollection() } returns mockedCollection

        // Create a custom FragmentFactory that provides the mocked CollectionService
        val fragmentFactory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                return when (className) {
                    CollectionFragment::class.java.name -> {
                        fragment
                    }
                    else -> super.instantiate(classLoader, className)
                }
            }
        }

        val scenario: FragmentScenario<CollectionFragment> = launchFragmentInContainer(
            themeResId = R.style.Theme_MobilnyCiuch,
            fragmentArgs = null,
            factory = fragmentFactory
        )

        // Set the fragment to the CREATED state
        scenario.moveToState(Lifecycle.State.CREATED)

        // Verify the initial state of the fragment's views or perform other assertions

        // Set the fragment to the STARTED state
        scenario.moveToState(Lifecycle.State.STARTED)

        // Interact with the fragment's views or perform other actions

        // Set the fragment to the RESUMED state
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Interact with the fragment's views or perform other actions

        // Set the fragment to the DESTROYED state
        scenario.moveToState(Lifecycle.State.DESTROYED)

        // Clean up the mocked dependencies
        unmockkAll()
    }
}
