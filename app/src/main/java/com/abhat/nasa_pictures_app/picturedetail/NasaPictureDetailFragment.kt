package com.abhat.nasa_pictures_app.picturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.abhat.nasa_pictures_app.R
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel
import kotlinx.android.synthetic.main.fragment_nasa_pictures_detail.*

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class NasaPictureDetailFragment: Fragment() {

    private var nasaPicturesViewModelModel: NasaPicturesViewModelModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nasa_pictures_detail, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readBundle()
        render()
    }

    private fun render() {
        nasaPicturesViewModelModel?.let { nasaPicturesViewModelModel ->
            iv_picture.load(nasaPicturesViewModelModel.url)
            tv_explanation.text = nasaPicturesViewModelModel.explaination
        }
    }

    private fun readBundle() {
        val bundle = arguments
        bundle?.let { bundle ->
            if (bundle.containsKey("nasa_pictures_model")) {
                nasaPicturesViewModelModel = bundle.getParcelable("nasa_pictures_model")
            }
        }
    }

    companion object {
        fun newInstance(nasaPicturesViewModelModel: NasaPicturesViewModelModel?): NasaPictureDetailFragment {
            val fragment = NasaPictureDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("nasa_pictures_model", nasaPicturesViewModelModel)
            fragment.arguments = bundle
            return fragment
        }
    }
}