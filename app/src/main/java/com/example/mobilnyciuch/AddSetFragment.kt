package com.example.mobilnyciuch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.mobilnyciuch.databinding.FragmentAddSetBinding
import com.example.mobinyciuch.services.CollectionViewModel
import com.example.mobinyciuch.services.ItemCollectionViewModel
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.listener.CarouselOnScrollListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

/**
 *  Fragment for adding new set to collection
 *  @property collectionService - service for collection
 *  @property itemCollectionService - service for item collection(sets)
 *  @property top_chosen - index of chosen top
 *  @property low_chosen - index of chosen low
 *  @property foot_chosen - index of chosen footwear
 */
class AddSetFragment : Fragment() {
    private val collectionService: CollectionViewModel by activityViewModels()
    private val itemCollectionService: ItemCollectionViewModel by activityViewModels()
    private var top_chosen: Int = 0
    private var low_chosen: Int = 0
    private var foot_chosen: Int = 0

    /**
     *  Function for creating view of fragment
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddSetBinding.inflate(inflater, container, false)
        val view = binding.root
        val carouselUp: ImageCarousel = binding.carouselUpper
        val carouselLow: ImageCarousel = binding.carouselLower
        val carouselFoot: ImageCarousel = binding.carouselFootwear
        val send = binding.buttonSet
        var top_item: View? = null
        var low_item: View? = null
        var foot_item: View? = null

        /**
         *  Adding initial data to carousels
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
         *  Setting click listener for send chosen set, to save it and navigate to preview
         */
        send.setOnClickListener {
            itemCollectionService.createSet(
                collectionService.getCollection().listUp[top_chosen],
                collectionService.getCollection().listLow[low_chosen],
                collectionService.getCollection().listFoot[foot_chosen]
            )
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(SetPreviewFragment(), false)
        }

        view.findViewById<Button>(R.id.button_collection).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return view
    }
}