package com.abhat.nasa_pictures_app.picturedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abhat.nasa_pictures_app.Constants.NASA_PICTURES_MODEL
import com.abhat.nasa_pictures_app.Constants.POSITION
import com.abhat.nasa_pictures_app.Constants.SIZE
import com.abhat.nasa_pictures_app.R
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel
import kotlinx.android.synthetic.main.fragment_nasa_pictures_detail.*

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class NasaPictureDetailFragment: Fragment() {

    private var position: Int = 0
    private var viewpagerSize: Int = 0
    private var nasaPicturesViewModelModel: List<NasaPicturesViewModelModel>? = null

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
        setupViewPager()
    }

    private fun setupViewPager() {
        activity?.let { activity ->
            vp_nasa_picture_details.adapter = NasaPicturesDetailPagerAdapter(activity)
            vp_nasa_picture_details.setCurrentItem( position, false)
        }
    }


    private fun readBundle() {
        val bundle = arguments
        bundle?.let { bundle ->
            if (bundle.containsKey(NASA_PICTURES_MODEL)) {
                nasaPicturesViewModelModel = bundle.getParcelableArrayList(NASA_PICTURES_MODEL)
            }
            if (bundle.containsKey(SIZE)) {
                viewpagerSize = bundle.getInt(SIZE)
            }
            if (bundle.containsKey(POSITION)) {
                position = bundle.getInt(POSITION)
            }
        }
    }

    inner class NasaPicturesDetailPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {
        override fun getItemCount(): Int {
            return viewpagerSize
        }

        override fun createFragment(pos: Int): Fragment {
            return NasaPictureDetailPagerFragment.newInstance(nasaPicturesViewModelModel?.get(pos))
        }
    }

    companion object {
        fun newInstance(position: Int, nasaPicturesSize: Int, nasaPicturesViewModelModelList: ArrayList<NasaPicturesViewModelModel>?): NasaPictureDetailFragment {
            val fragment = NasaPictureDetailFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION, position)
            bundle.putInt(SIZE, nasaPicturesSize)
            bundle.putParcelableArrayList(NASA_PICTURES_MODEL, nasaPicturesViewModelModelList)
            fragment.arguments = bundle
            return fragment
        }
    }
}