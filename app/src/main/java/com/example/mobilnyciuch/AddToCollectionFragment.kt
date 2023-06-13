package com.example.mobilnyciuch

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.example.mobilnyciuch.databinding.FragmentAddToCollectionBinding
import com.example.mobinyciuch.services.CollectionViewModel
import java.io.FileDescriptor
import java.io.IOException
import androidx.fragment.app.activityViewModels

class AddToCollectionFragment() : Fragment() {
    private val collectionService: CollectionViewModel by activityViewModels()
    private var _binding: FragmentAddToCollectionBinding? = null
    private val binding get() = _binding!!
    var image_uri: Uri? = null
    private val RESULT_LOAD_IMAGE = 123
    val IMAGE_CAPTURE_CODE = 654
    private var selectedImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddToCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        selectedImage = binding.selectedImage
        selectedImage!!.setOnClickListener(View.OnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE)
        })
        view.findViewById<Button>(R.id.button_add_return).setOnClickListener {
            var navRegister = activity as FragmentNawigation
            navRegister.navigateFrag(MenuFragment(), false)
        }
        return view

    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == IMAGE_CAPTURE_CODE && resultCode == Activity.RESULT_OK) {
            selectedImage?.setImageURI(image_uri)
            val bitmap = uriToBitmap(image_uri!!)
            selectedImage?.setImageBitmap(bitmap)
        }
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            image_uri = data.data
            selectedImage?.setImageURI(image_uri)
        }

        collectionService.addCollectionItem(image_uri, "upper")
    }

    private fun uriToBitmap(selectedFileUri: Uri): Bitmap? {
        try {
            val contentResolver = requireActivity().contentResolver
            val parcelFileDescriptor = contentResolver.openFileDescriptor(selectedFileUri, "r")
            val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
            val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
            parcelFileDescriptor.close()
            return image
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}