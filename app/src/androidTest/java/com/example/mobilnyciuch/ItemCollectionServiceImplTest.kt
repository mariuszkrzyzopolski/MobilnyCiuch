package com.example.mobilnyciuch

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobinyciuch.services.ItemCollectionService
import com.example.mobinyciuch.services.ItemCollectionServiceImpl
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ItemCollectionServiceImplTest {

    private lateinit var itemCollectionService: ItemCollectionService

    @Before
    fun setUp() {
        itemCollectionService = ItemCollectionServiceImpl()
    }

    @Test
    fun testGetSets() {
        val sets = itemCollectionService.getSets()
        // Assert that the returned sets is not null
        assertNotNull(sets)
        // Assert that the initial sets size is as expected
        assertEquals(2, sets.size)
    }

    @Test
    fun testRemoveSet() {
        val sets = itemCollectionService.getSets()
        val initialSize = sets.size

        // Remove a set
        itemCollectionService.removeSet(1)

        // Assert that the size of the sets has decreased by 1
        assertEquals(initialSize - 1, sets.size)
    }

    @Test
    fun testCreateSet() {
        val sets = itemCollectionService.getSets()
        val initialSize = sets.size

        // Create a new set
        itemCollectionService.createSet(listOf(1, 2, 3))

        // Assert that the size of the sets has increased by 1
        assertEquals(initialSize + 1, sets.size)
    }
}
