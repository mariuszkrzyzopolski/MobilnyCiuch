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


class CollectionFragment() : Fragment() {
    private val collectionService: CollectionViewModel by activityViewModels()
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!
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

        carouselUp.addData(collectionService.getCollection().listUp)
        carouselLow.addData(collectionService.getCollection().listLow)
        carouselFoot.addData(collectionService.getCollection().listFoot)

        carouselUp.onScrollListener = object : CarouselOnScrollListener {
            override fun onScrollStateChanged(
                recyclerView: RecyclerView,
                newState: Int,
                position: Int,
                carouselItem: CarouselItem?
            ) {
                top_chosen = position
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
            }
        }

        delete_up.setOnClickListener {
            collectionService.getCollection().listUp.removeAt(top_chosen)
            collectionService.removeCollectionItem(top_chosen, "up")
        }

        delete_low.setOnClickListener {
            collectionService.getCollection().listLow.removeAt(low_chosen)
            collectionService.removeCollectionItem(low_chosen, "low")
        }

        delete_foot.setOnClickListener {
            collectionService.getCollection().listFoot.removeAt(foot_chosen)
            collectionService.removeCollectionItem(foot_chosen, "foot")
        }

        view.findViewById<Button>(R.id.button_collection).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return view
    }
}