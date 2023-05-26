package com.example.mobilnyciuch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mobilnyciuch.databinding.FragmentCollectionBinding
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CollectionFragment : Fragment() {
    private val listUp = mutableListOf<CarouselItem>()
    private val listlow = mutableListOf<CarouselItem>()
    private val listfoot = mutableListOf<CarouselItem>()
    private var _binding: FragmentCollectionBinding? = null
    private val binding get() = _binding!!

    private fun createCarouselLists() {
        listUp.add(
            CarouselItem(
                imageDrawable = R.drawable.up1,
            )
        )

        listUp.add(
            CarouselItem(
                imageDrawable= R.drawable.up2
            )
        )

        listUp.add(
            CarouselItem(
                imageDrawable = R.drawable.up3,
            )
        )

        listUp.add(
            CarouselItem(
                imageDrawable = R.drawable.up4,
            )
        )

        listlow.add(
            CarouselItem(
                imageDrawable = R.drawable.low1,
            )
        )

        listlow.add(
            CarouselItem(
                imageDrawable = R.drawable.low2,
            )
        )

        listlow.add(
            CarouselItem(
                imageDrawable = R.drawable.low3,
            )
        )

        listfoot.add(
            CarouselItem(
                imageDrawable = R.drawable.foot1,
            )
        )

        listfoot.add(
            CarouselItem(
                imageDrawable = R.drawable.foot2,
            )
        )

        listfoot.add(
            CarouselItem(
                imageDrawable = R.drawable.foot3,
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        val carouselUp: ImageCarousel = binding.carouselUpper
        val carouselLow: ImageCarousel = binding.carouselLower
        val carouselFoot: ImageCarousel = binding.carouselFootwear

        createCarouselLists()
        carouselUp.addData(listUp)
        carouselLow.addData(listlow)
        carouselFoot.addData(listfoot)

        view.findViewById<Button>(R.id.button_collection).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return view
    }
}