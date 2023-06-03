package com.example.mobilnyciuch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobilnyciuch.databinding.FragmentAddToCollectionBinding

class AddToCollectionFragment : Fragment() {
    private var _binding: FragmentAddToCollectionBinding? = null
    private val binding get() = _binding!!
    var image_uri: Uri? = null
    private val RESULT_LOAD_IMAGE = 123
    val IMAGE_CAPTURE_CODE = 654
    val selectedImage = binding.selectedImage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddToCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        selectedImage?.setOnClickListener(View.OnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
        })
        return view

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
//            selectedImage!!.setImageURI(image_uri)
//            val bitmap = uriToBitmap(image_uri!!)
//            selectedImage!!.setImageBitmap(bitmap)
//        }
//        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
//            image_uri = data.data
//            selectedImage!!.setImageURI(image_uri)
//        }
    }
}