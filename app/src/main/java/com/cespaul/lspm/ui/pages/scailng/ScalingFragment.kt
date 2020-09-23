package com.cespaul.lspm.ui.pages.scailng

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment
import com.cespaul.lspm.ui.MainActivity.Companion.REQUEST_IMAGE_CAPTURE
import com.cespaul.lspm.ui.MainActivity.Companion.REQUEST_IMAGE_GALLERY
import kotlinx.android.synthetic.main.fragment_scaling.*

class ScalingFragment : BaseFragment<ScalingPresenter>(), ScalingView {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_scaling, container, false)
    }

    override fun instantiatePresenter(): ScalingPresenter {
        return ScalingPresenter(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        open_gallery_button.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, REQUEST_IMAGE_GALLERY)
        }
        take_photo_button.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureIntent.resolveActivity(requireContext().packageManager)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }

        refresh_button.setOnClickListener {
            scalingButtonsGroup.visibility = View.VISIBLE
            resultImage.visibility = View.INVISIBLE

            refresh_button.hide()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            scalingButtonsGroup.visibility = View.INVISIBLE
            resultImage.visibility = View.VISIBLE
            refresh_button.show()
            when (requestCode) {
                REQUEST_IMAGE_GALLERY -> {
                    val selectedImageURI = data.data
                    selectedImageURI.let {
                        resultImage.setImageURI(selectedImageURI)
                    }
                }

                REQUEST_IMAGE_CAPTURE -> {
                    val selectedPhotoBitmap = data.extras?.get("data") as Bitmap
                    resultImage.setImageBitmap(selectedPhotoBitmap)
                }
            }
        }
    }
}