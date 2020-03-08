package com.qifan.theforktest.ui.fragment.detail

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.domain.model.RestaurantModel
import com.qifan.theforktest.R
import com.qifan.theforktest.extension.inflateLayout
import com.qifan.theforktest.extension.load

class RestaurantSlideAdapter : RecyclerView.Adapter<RestaurantSlideAdapter.SlideViewHolder>() {
    private val slides = mutableListOf<RestaurantModel.Slide>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideViewHolder {
        return SlideViewHolder(parent.inflateLayout(R.layout.slide))
    }

    override fun getItemCount(): Int = slides.size

    override fun onBindViewHolder(holder: SlideViewHolder, position: Int) {
        with(slides[position]) {
            holder.slide.load(url)
        }
    }

    fun setData(data: List<RestaurantModel.Slide>) {
        slides.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class SlideViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val slide: AppCompatImageView = itemView.findViewById(R.id.slide)
    }
}