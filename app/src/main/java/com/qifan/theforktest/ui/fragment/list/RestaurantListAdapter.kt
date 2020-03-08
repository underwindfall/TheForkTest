package com.qifan.theforktest.ui.fragment.list

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.theforktest.R
import com.qifan.theforktest.extension.checkNullOrHideView
import com.qifan.theforktest.extension.inflateLayout
import com.qifan.theforktest.extension.load
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import kotlin.math.truncate

class RestaurantListAdapter : RecyclerView.Adapter<RestaurantListAdapter.RestaurantViewHolder>() {
    private val restaurants: MutableList<RestaurantBannerModel> = mutableListOf()
    private val mOnItemSelected: PublishProcessor<RestaurantId> = PublishProcessor.create()

    val onItemSelected: Flowable<RestaurantId> = mOnItemSelected

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(parent.inflateLayout(R.layout.restaurant_item_view))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        with(restaurants[position]) {
            holder.title.text = name
            holder.des.text = speciality
            checkNullOrHideView(holder.rate, averageRate) {
                holder.rate.text = truncate(it).toString()
            }
            checkNullOrHideView(holder.price, price) {
                holder.price.text = formatPrice(it)
            }
            checkNullOrHideView(holder.banner, bannerUrl) {
                holder.banner.load(it)
            }

            holder.itemView.setOnClickListener {
                mOnItemSelected.onNext(id)
            }
        }
    }

    private fun formatPrice(price: RestaurantBannerModel.Price): String {
        return "${price.currency.symbol} ${price.amount}"
    }

    override fun getItemCount(): Int = restaurants.size


    fun setData(data: List<RestaurantBannerModel>) {
        restaurants.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val banner: AppCompatImageView = itemView.findViewById(R.id.banner)
        internal val title: AppCompatTextView = itemView.findViewById(R.id.title)
        internal val des: AppCompatTextView = itemView.findViewById(R.id.description)
        internal val rate: AppCompatTextView = itemView.findViewById(R.id.rate)
        internal val price: AppCompatTextView = itemView.findViewById(R.id.price)
    }

}