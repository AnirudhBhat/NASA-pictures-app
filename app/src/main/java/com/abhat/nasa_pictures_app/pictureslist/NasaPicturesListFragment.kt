package com.abhat.nasa_pictures_app.pictureslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abhat.nasa_pictures_app.R
import com.abhat.nasa_pictures_app.picturedetail.NasaPictureDetailFragment
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.NasaPicturesListViewModel
import kotlinx.android.synthetic.main.fragment_nasa_pictures_list.*
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class NasaPicturesListFragment: Fragment() {

    private var nasaPicturesListUIState: NasaPicturesListUIState? = null
    private val nasaPicturesListViewModel: NasaPicturesListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nasa_pictures_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeViewModel()
        nasaPicturesListViewModel.onAction(NasaPicturesListViewModel.Action.GetNasaPictures)
    }

    private fun observeViewModel() {
        nasaPicturesListViewModel.getNasaPicturesListUIState().observe(viewLifecycleOwner, Observer {
            this.nasaPicturesListUIState = it
            render(it)
        })
    }

    private fun render(nasaPicturesListUIState: NasaPicturesListUIState?) {
        nasaPicturesListUIState?.picturesList?.let {
            (rv_nasa_pictures.adapter as? NasaPicturesListAdapter)?.updateNasaPictures(it)
        }
    }

    private fun setupRecyclerView() {
        with (rv_nasa_pictures) {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = NasaPicturesListAdapter(null) { position, nasaPictureViewModelModel ->
                createFragment(NasaPictureDetailFragment.newInstance(position, nasaPicturesListUIState?.picturesList?.size ?: 0, ArrayList(nasaPicturesListUIState?.picturesList ?: listOf())))
            }
            (adapter as NasaPicturesListAdapter).stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
        }
    }

    private fun createFragment(fragment: Fragment) {
        val ft = activity?.supportFragmentManager?.beginTransaction()
        ft?.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        ft?.replace(R.id.container, fragment)
        ft?.addToBackStack(null)
        ft?.commit()
    }

    companion object {
        fun newInstance(): NasaPicturesListFragment {
            val fragment = NasaPicturesListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}