package com.example.mobinyciuch.services

import androidx.lifecycle.ViewModel
import com.example.mobilnyciuch.R
import com.example.mobinyciuch.services.ItemCollectionServiceImpl.Set
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

interface ItemCollectionService {
    fun getSets(): MutableList<Set>
    fun removeSet(item: Int)
    fun createSet(collection: List<Int>)
}

public class ItemCollectionViewModel(var itemCollectionService: ItemCollectionService): ViewModel() {

    fun getSets(): MutableList<Set> {
        return itemCollectionService.getSets()
    }

    fun removeSet(item: Int) {
        itemCollectionService.removeSet(item)
    }

    fun createSet(carouselItem: CarouselItem, carouselItem1: CarouselItem, carouselItem2: CarouselItem) {
        var list = listOf(carouselItem.imageDrawable, carouselItem1.imageDrawable, carouselItem2.imageDrawable)
        itemCollectionService.createSet(list as List<Int>)
    }
}

class ItemCollectionServiceImpl : ItemCollectionService {
    private val sets: MutableList<Set> =
        mutableListOf(
            Set(1, listOf(R.drawable.up1, R.drawable.low1, R.drawable.foot1)),
            Set(2, listOf(R.drawable.up2, R.drawable.low2, R.drawable.foot2))
        )

    override fun getSets(): MutableList<Set> {
        return sets
    }

    override fun removeSet(id: Int) {
        val setToRemove = sets.find { set ->
            set.id == id
        }

        if (setToRemove != null) {
            sets.remove(setToRemove)
        }
    }

    override fun createSet(collection: List<Int>) {
        val id = sets.size + 1
        sets.add(Set(id, collection))
    }

    data class Set(
        val id: Int,
        val collection: List<Int>
    )
}