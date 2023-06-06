package com.example.mobilnyciuch

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.mobinyciuch.services.ItemCollectionService
import com.example.mobinyciuch.services.ItemCollectionServiceImpl
import androidx.lifecycle.lifecycleScope


class SetPreviewFragment : Fragment() {
    private lateinit var itemCollectionService: ItemCollectionService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_set_preview, container, false)
        itemCollectionService = ItemCollectionServiceImpl()

        lifecycleScope.launch {
            val sets = withContext(Dispatchers.IO) {
                itemCollectionService.getSets()
            }

            updateUIWithSets(sets, rootView)
        }

        rootView.findViewById<Button>(R.id.sets_back).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }

        return rootView
    }

    private fun updateUIWithSets(sets: List<ItemCollectionServiceImpl.Set>, rootView: View) {
        val linearLayout = rootView.findViewById<LinearLayout>(R.id.linearLayoutImages)

        if (sets.isEmpty()) {
            showEmpty(linearLayout)
        } else {
            for (set in sets) {
                val cardView = layoutInflater.inflate(R.layout.set_card_item, linearLayout, false) as CardView
                val imageView1 = cardView.findViewById<ImageView>(R.id.image1)
                val imageView2 = cardView.findViewById<ImageView>(R.id.image2)
                val imageView3 = cardView.findViewById<ImageView>(R.id.image3)
                val btnClose = cardView.findViewById<ImageButton>(R.id.btnClose)
                val setText = cardView.findViewById<TextView>(R.id.setText)

                setText.text = "Zestaw nr ${set.id}"

                // Set the images for each ImageView using the set's collection
                imageView1.setImageResource(set.collection[0])
                imageView2.setImageResource(set.collection[1])
                imageView3.setImageResource(set.collection[2])

                // Set a click listener for the 'X' button
                btnClose.setOnClickListener {
                    // Remove the set from the ItemCollectionService
                    itemCollectionService.removeSet(set.id)

                    linearLayout.removeView(cardView)

                    if (sets.isEmpty()) {
                        showEmpty(linearLayout)
                    }
                }

                linearLayout.addView(cardView)
            }
        }
    }

    private fun showEmpty(linearLayout: LinearLayout) {
        val noSetsTextView = TextView(requireContext())
        noSetsTextView.text = "Użytkownik nie posiada żadnego zestawu"
        noSetsTextView.gravity = Gravity.CENTER

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        noSetsTextView.layoutParams = layoutParams

        linearLayout.addView(noSetsTextView)
    }
}
