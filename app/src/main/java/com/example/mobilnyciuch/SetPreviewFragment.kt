package com.example.mobilnyciuch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout


class SetPreviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_set_preview, container, false)

        val imageResources = listOf(
            R.drawable.up1,
            R.drawable.low1,
            R.drawable.foot1
        )

        val linearLayout = rootView.findViewById<LinearLayout>(R.id.linearLayoutImages)

        for (imageResource in imageResources) {
            val imageView = ImageView(requireContext())
            imageView.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.setImageResource(imageResource)

            linearLayout.addView(imageView)
        }

        return rootView
    }
}
