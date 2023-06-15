package com.example.mobinyciuch.services

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.example.mobilnyciuch.R
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

/**
 * CollectionService - interface for collection service
 */
interface CollectionService {
    fun getCollection(): Collection
    fun addCollectionItem(url: Uri?, type: String)
    fun removeCollectionItem(item: Int, carousel: String)
}

/**
 * CollectionViewModel - shared view model for collection
 * @property collectionService CollectionServiceImpl
 */
class CollectionViewModel: ViewModel() {
    private val collectionService = CollectionServiceImpl()

    fun getCollection(): Collection {
        return collectionService.getCollection()
    }

    fun addCollectionItem(url: Uri?, type: String) {
        collectionService.addCollectionItem(url, type)
    }

    fun removeCollectionItem(item: Int, carousel: String) {
        collectionService.removeCollectionItem(item, carousel)
    }
}

/**
 * CollectionServiceImpl - implementation of CollectionService
 * @property collection Collection
 */
class CollectionServiceImpl : CollectionService {
    private var collection: Collection = init()

    /**
     * init - function for init collection
     * @return Collection
     */
    fun init(): Collection {
        collection = Collection(mutableListOf(
            CarouselItem(imageDrawable = R.drawable.up1),
            CarouselItem(imageDrawable = R.drawable.up2),
            CarouselItem(imageDrawable = R.drawable.up3),
            CarouselItem(imageDrawable = R.drawable.up4),
        ), mutableListOf(
            CarouselItem(imageDrawable = R.drawable.low1),
            CarouselItem(imageDrawable = R.drawable.low2),
            CarouselItem(imageDrawable = R.drawable.low3),
        ), mutableListOf(
            CarouselItem(imageDrawable = R.drawable.foot1),
            CarouselItem(imageDrawable = R.drawable.foot2),
            CarouselItem(imageDrawable = R.drawable.foot3),
        ))
        return collection
    }

    /**
     * getCollection - function for get collection
     * @return Collection
     */
    override fun getCollection(): Collection {
        return collection
    }

    /**
     * addCollectionItem - function for add collection item
     * @param url local path to image
     * @param type String
     */
    override fun addCollectionItem(url: Uri?, type: String) {
        val item = CarouselItem(imageUrl=url.toString())
        if (type == "upper")
            collection.listUp.add(item)
        else if (type == "lower")
            collection.listLow.add(item)
        else if (type == "footwear")
            collection.listFoot.add(item)
    }

    /**
     * removeCollectionItem - function for remove collection item
     * @param item index of item
     * @param carousel type of carousel
     */
    override fun removeCollectionItem(item: Int, carousel: String) {
        if (carousel == "upper")
            collection.listUp.removeAt(item)
        else if (carousel == "lower")
            collection.listLow.removeAt(item)
        else if (carousel == "footwear")
            collection.listFoot.removeAt(item)
    }
}

/**
 * Collection - class for collection
 * @property listUp MutableList<CarouselItem>
 * @property listLow MutableList<CarouselItem>
 * @property listFoot MutableList<CarouselItem>
 */
data class Collection(
    val listUp: MutableList<CarouselItem>,
    val listLow: MutableList<CarouselItem>,
    val listFoot: MutableList<CarouselItem>
)
