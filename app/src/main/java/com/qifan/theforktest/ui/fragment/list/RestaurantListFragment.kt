package com.qifan.theforktest.ui.fragment.list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.viewmodel.getInjectViewModel
import com.qifan.theforktest.ui.base.view.fragment.InjectionFragment
import com.qifan.theforktest.ui.decorator.MarginItemDecorator
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class RestaurantListFragment : InjectionFragment() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var restaurantListViewModel: RestaurantListViewModel

    private lateinit var restaurantListAdapter: RestaurantListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        restaurantListViewModel = getInjectViewModel(viewModelFactory)
    }

    override fun getLayoutId(): Int = R.layout.fragment_list


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        compositeDisposable.add(
            getRestaurantList().subscribe { it ->
                restaurantListAdapter.setData(it)
            }
        )
    }

    private fun setupRecyclerView() {
        rv_restaurants.apply {
            layoutManager = GridLayoutManager(context, 2).apply {
                with(
                    MarginItemDecorator(
                        MarginItemDecorator.Space(
                            space = resources.getDimension(R.dimen.list_gap_default).toInt()
                        )
                    )
                ) {
                    addItemDecoration(this)
                }
            }

            restaurantListAdapter = RestaurantListAdapter()
            adapter = restaurantListAdapter
        }
    }


    private fun getRestaurantList(): Single<List<RestaurantBannerModel>> {
        return restaurantListViewModel.getListRestaurant(listOf("40370", "16409", "14163", "40171"))
            .observeOn(AndroidSchedulers.mainThread())
    }

}