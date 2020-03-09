package com.qifan.theforktest.ui.restaurant

import android.os.Bundle
import android.widget.Toast
import com.qifan.theforktest.R
import com.qifan.theforktest.di.viewmodel.ViewModelFactory
import com.qifan.theforktest.extension.viewmodel.getInjectViewModel
import com.qifan.theforktest.ui.base.view.activity.InjectionActivity
import com.qifan.theforktest.ui.notifier.ErrorListener
import com.qifan.theforktest.ui.restaurant.fragment.detail.RestaurantDetailFragment
import com.qifan.theforktest.ui.restaurant.fragment.search.RestaurantSearchFragment
import com.qifan.theforktest.ui.restaurant.fragment.search.RouteCallBack
import javax.inject.Inject

class RestaurantActivity : InjectionActivity(),
    ErrorListener,
    RouteCallBack {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private lateinit var detailViewModel: RestaurantDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    RestaurantSearchFragment()
                )
                .commit()
        }

        detailViewModel = getInjectViewModel(viewModelFactory)
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun showError(error: Throwable?) {
        Toast.makeText(this, error?.message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToDetail() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                RestaurantDetailFragment()
            )
            .addToBackStack("Detail")
            .commit()
    }

    override fun onBackPressed() {
        detailViewModel.restaurantDetail.reset()
        super.onBackPressed()
    }
}
