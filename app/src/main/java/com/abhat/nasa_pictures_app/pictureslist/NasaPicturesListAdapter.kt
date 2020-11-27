package com.abhat.nasa_pictures_app.pictureslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.abhat.nasa_pictures_app.R
import com.abhat.nasa_pictures_app.pictureslist.viewmodel.model.NasaPicturesViewModelModel
import kotlinx.android.synthetic.main.item_nasa_pictures_list.view.*

/**
 * Created by Anirudh Uppunda on 27,November,2020
 */
class NasaPicturesListAdapter(private var nasaPicturesList: List<NasaPicturesViewModelModel>?) :
    RecyclerView.Adapter<NasaPicturesListAdapter.NasaPicturesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NasaPicturesListViewHolder {
        return NasaPicturesListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_nasa_pictures_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return nasaPicturesList?.size ?: 0
    }

    override fun onBindViewHolder(holder: NasaPicturesListViewHolder, position: Int) {
        holder.bind()
    }

    inner class NasaPicturesListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            with(itemView) {
                iv_nasa_picture.load(nasaPicturesList?.get(position)?.url) {
                    crossfade(true)
                    placeholder(android.R.color.darker_gray)
                }
            }
        }
    }

    fun updateNasaPictures(nasaPicturesList: List<NasaPicturesViewModelModel>) {
        this.nasaPicturesList = nasaPicturesList
        notifyDataSetChanged()
    }
}
