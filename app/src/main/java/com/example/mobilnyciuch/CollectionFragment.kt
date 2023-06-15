package com.example.mobilnyciuch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilnyciuch.databinding.FragmentCollectionBinding
import com.example.mobinyciuch.services.CollectionViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


/**
 * Fragment for collection view
 * @property collectionService service for collection
 * @property top_chosen index of chosen top
 * @property low_chosen index of chosen low
 * @property foot_chosen index of chosen footwear
 */
class CollectionFragment() : Fragment() {
    private val collectionService: CollectionViewModel by activityViewModels()
    private var top_chosen: Int = 0
    private var low_chosen: Int = 0
    private var foot_chosen: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        val carouselUp: ImageCarousel = binding.carouselUpper
        val carouselLow: ImageCarousel = binding.carouselLower
        val carouselFoot: ImageCarousel = binding.carouselFootwear
        val delete_up: ImageButton = binding.deleteUp
        val delete_low: ImageButton = binding.deleteLow
        val delete_foot: ImageButton = binding.deleteFoot
        val linearLayout = binding.linearCollection
        var top_item: View? = null
        var low_item: View? = null
        var foot_item: View? = null

        /**
         * Adding initial data to carousels
         */
        carouselUp.addData(collectionService.getCollection().listUp)
        carouselLow.addData(collectionService.getCollection().listLow)
        carouselFoot.addData(collectionService.getCollection().listFoot)

        /**
         *  Setting change listeners for carousels(during scroll)
         */
        carouselUp.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                top_chosen = position
                top_item = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            }
        }

        carouselLow.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                low_chosen = position
                low_item = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            }
        }

        carouselFoot.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                foot_chosen = position
                foot_item = recyclerView.findViewHolderForAdapterPosition(position)?.itemView
            }
        }

        /**
         *  Setting click listener for delete current centered item
         */
        delete_up.setOnClickListener {
            collectionService.getCollection().listUp.removeAt(top_chosen)
            collectionService.removeCollectionItem(top_chosen, "up")
            linearLayout.removeView(top_item)
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(CollectionFragment(), false)
        }

        delete_low.setOnClickListener {
            collectionService.getCollection().listLow.removeAt(low_chosen)
            collectionService.removeCollectionItem(low_chosen, "low")
            linearLayout.removeView(low_item)
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(CollectionFragment(), false)
        }

        delete_foot.setOnClickListener {
            collectionService.getCollection().listFoot.removeAt(foot_chosen)
            collectionService.removeCollectionItem(foot_chosen, "foot")
            linearLayout.removeView(foot_item)
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(CollectionFragment(), false)
        }

        view.findViewById<Button>(R.id.button_collection).setOnClickListener {
            val navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return view
    }
}