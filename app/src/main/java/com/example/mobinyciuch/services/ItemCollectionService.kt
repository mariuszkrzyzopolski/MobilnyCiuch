package com.example.mobinyciuch.services

import com.example.mobilnyciuch.R
import com.example.mobinyciuch.services.ItemCollectionServiceImpl.Set

interface ItemCollectionService {
    fun getSets(): MutableList<Set>
    fun removeSet(item: Int)
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

    data class Set(
        val id: Int,
        val collection: List<Int>
    )
}