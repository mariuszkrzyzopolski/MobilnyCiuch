package com.example.mobinyciuch.services

import androidx.lifecycle.ViewModel
import com.example.mobilnyciuch.R
import com.example.mobinyciuch.services.ItemCollectionServiceImpl.Set
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

/**
 * interface for Sets
 */
interface ItemCollectionService {
    fun getSets(): MutableList<Set>
    fun removeSet(item: Int)
    fun createSet(collection: List<Int>)
}

/**
 * shared ViewModel for Sets
 * @property itemCollectionService ItemCollectionServiceImpl
 */
class ItemCollectionViewModel: ViewModel() {
    private val itemCollectionService = ItemCollectionServiceImpl()

    /**
     * getSets - function for get sets
     * @return MutableList<Set>
     */
    fun getSets(): MutableList<Set> {
        return itemCollectionService.getSets()
    }

    /**
     * removeSet - function for remove set
     * @param item Int
     */
    fun removeSet(item: Int) {
        itemCollectionService.removeSet(item)
    }

    /**
     * createSet - function for create set
     * @param carouselItem CarouselItem
     * @param carouselItem1 CarouselItem
     * @param carouselItem2 CarouselItem
     */
    fun createSet(carouselItem: CarouselItem, carouselItem1: CarouselItem, carouselItem2: CarouselItem) {
        var list = listOf(carouselItem.imageDrawable, carouselItem1.imageDrawable, carouselItem2.imageDrawable)
        itemCollectionService.createSet(list as List<Int>)
    }
}

/**
 * implementation of ItemCollectionService for sets
 * @property sets MutableList<Set> - initial sets
 */
class ItemCollectionServiceImpl : ItemCollectionService {
    private val sets: MutableList<Set> =
        mutableListOf(
            Set(1, listOf(R.drawable.up1, R.drawable.low1, R.drawable.foot1)),
            Set(2, listOf(R.drawable.up2, R.drawable.low2, R.drawable.foot2))
        )

    /**
     * getSets - function for get sets
     * @return MutableList<Set>
     */
    override fun getSets(): MutableList<Set> {
        return sets
    }

    /**
     * removeSet - function for remove set
     * @param id id of set
     */
    override fun removeSet(id: Int) {
        val setToRemove = sets.find { set ->
            set.id == id
        }

        if (setToRemove != null) {
            sets.remove(setToRemove)
        }
    }


    /**
     * createSet - function for create set
     * @param collection List of item indexes
     */
    override fun createSet(collection: List<Int>) {
        val id = sets.size + 1
        sets.add(Set(id, collection))
    }

    /**
     * Set - data class for set
     * @property id index of set
     * @property collection List<Int>
     */
    data class Set(
        val id: Int,
        val collection: List<Int>
    )
}