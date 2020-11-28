package com.abhat.nasa_pictures_app.picturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.abhat.nasa_pictures_app.Constants.NASA_PICTURES_MODEL
import com.abhat.nasa_pictures_app.R
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel
import kotlinx.android.synthetic.main.item_viewpager_fragment_nasa_pictures.*

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class NasaPictureDetailPagerFragment: Fragment() {

    private var nasaPicturesViewModelModel: NasaPicturesViewModelModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_viewpager_fragment_nasa_pictures, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        readBundle()
        render()
    }

    private fun readBundle() {
        val bundle = arguments
        bundle?.let { bundle ->
            if (bundle.containsKey(NASA_PICTURES_MODEL)) {
                nasaPicturesViewModelModel = bundle.getParcelable(NASA_PICTURES_MODEL)
            }
        }
    }

    private fun render() {
        nasaPicturesViewModelModel?.let { nasaPicturesViewModelModel ->
            iv_picture.load(nasaPicturesViewModelModel.url)
            tv_explanation.text = nasaPicturesViewModelModel.explaination
            tv_title.text = nasaPicturesViewModelModel.title
            tv_date.text = nasaPicturesViewModelModel.date
        }
    }

    companion object {
        fun newInstance(nasaPicturesViewModelModel: NasaPicturesViewModelModel?): NasaPictureDetailPagerFragment {
            val fragment = NasaPictureDetailPagerFragment()
            val bundle = Bundle()
            bundle.putParcelable(NASA_PICTURES_MODEL, nasaPicturesViewModelModel)
            fragment.arguments = bundle
            return fragment
        }
    }
}