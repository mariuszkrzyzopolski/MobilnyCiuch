package com.example.mobilnyciuch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mobilnyciuch.databinding.FragmentCollectionBinding
import com.example.mobinyciuch.services.CollectionServiceImpl
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class CollectionFragment : Fragment() {
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        val carouselUp: ImageCarousel = binding.carouselUpper
        val carouselLow: ImageCarousel = binding.carouselLower
        val carouselFoot: ImageCarousel = binding.carouselFootwear

        val collection = CollectionServiceImpl().getCollection()

        carouselUp.addData(collection.listUp)
        carouselLow.addData(collection.listLow)
        carouselFoot.addData(collection.listFoot)

        view.findViewById<Button>(R.id.button_collection).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return view
    }
}