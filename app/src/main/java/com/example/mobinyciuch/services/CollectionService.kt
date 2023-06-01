package com.example.mobinyciuch.services

import com.example.mobilnyciuch.R
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

interface CollectionService {
    fun getCollection(): Collection
    fun addCollectionItem(item: CarouselItem)
    fun removeCollectionItem(item: CarouselItem)
}

class CollectionServiceImpl : CollectionService {
    private val collection = Collection(listOf(), listOf(), listOf())

    override fun getCollection(): Collection {
        val listUp = listOf(
            CarouselItem(imageDrawable = R.drawable.up1),
            CarouselItem(imageDrawable = R.drawable.up2),
            CarouselItem(imageDrawable = R.drawable.up3),
            CarouselItem(imageDrawable = R.drawable.up4),
        )

        val listLow = listOf(
            CarouselItem(imageDrawable = R.drawable.low1),
            CarouselItem(imageDrawable = R.drawable.low2),
            CarouselItem(imageDrawable = R.drawable.low3),
        )

        val listFoot = listOf(
            CarouselItem(imageDrawable = R.drawable.foot1),
            CarouselItem(imageDrawable = R.drawable.foot2),
            CarouselItem(imageDrawable = R.drawable.foot3),
        )

        return Collection(listUp, listLow, listFoot)
    }

    override fun addCollectionItem(item: CarouselItem) {
        if (item.headers?.get("type") == "upper")
            collection.listUp.plus(item)
        else if (item.headers?.get("type") == "lower")
            collection.listLow.plus(item)
        else if (item.headers?.get("type") == "footwear")
            collection.listFoot.plus(item)
    }

    override fun removeCollectionItem(item: CarouselItem) {
        if (item.headers?.get("type") == "upper")
            collection.listUp.minus(item)
        else if (item.headers?.get("type") == "lower")
            collection.listLow.minus(item)
        else if (item.headers?.get("type") == "footwear")
            collection.listFoot.minus(item)
    }
}

data class Collection(
    val listUp: List<CarouselItem>,
    val listLow: List<CarouselItem>,
    val listFoot: List<CarouselItem>
)
