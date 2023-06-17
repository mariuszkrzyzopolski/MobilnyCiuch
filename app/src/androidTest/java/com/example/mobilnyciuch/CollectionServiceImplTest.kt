package com.example.mobilnyciuch

import android.net.Uri
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobinyciuch.services.CollectionService
import com.example.mobinyciuch.services.CollectionServiceImpl
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CollectionServiceImplTest {

    private lateinit var collectionService: CollectionService

    @Before
    fun setUp() {
        collectionService = CollectionServiceImpl()
    }

    @Test
    fun testGetCollection() {
        val collection = collectionService.getCollection()
        // Assert that the returned collection is not null
        assertNotNull(collection)
        // Assert that the initial collection size is as expected
        assertEquals(4, collection.listUp.size)
        assertEquals(3, collection.listLow.size)
        assertEquals(3, collection.listFoot.size)
    }

    @Test
    fun testAddCollectionItem() {
        val collection = collectionService.getCollection()
        val initialSize = collection.listUp.size

        // Add a new collection item
        collectionService.addCollectionItem(Uri.parse("file://path/to/image.png"), "upper")

        // Assert that the size of the 'listUp' has increased by 1
        assertEquals(initialSize + 1, collection.listUp.size)
    }

    @Test
    fun testRemoveCollectionItem() {
        val collection = collectionService.getCollection()
        val initialSize = collection.listLow.size

        // Remove a collection item
        collectionService.removeCollectionItem(0, "lower")

        // Assert that the size of the 'listLow' has decreased by 1
        assertEquals(initialSize - 1, collection.listLow.size)
    }
}