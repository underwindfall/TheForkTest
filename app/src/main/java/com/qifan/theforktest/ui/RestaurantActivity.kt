package com.qifan.theforktest.ui

import android.os.Bundle
import android.widget.Toast
import com.qifan.domain.respository.RestaurantId
import com.qifan.theforktest.R
import com.qifan.theforktest.ui.base.view.activity.InjectionActivity
import com.qifan.theforktest.ui.fragment.detail.RestaurantDetailFragment
import com.qifan.theforktest.ui.fragment.list.RestaurantListFragment
import com.qifan.theforktest.ui.fragment.list.RouteCallBack
import com.qifan.theforktest.ui.notifier.ErrorListener

class RestaurantActivity : InjectionActivity(),
    ErrorListener,
    RouteCallBack {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(
                    R.id.container,
                    RestaurantListFragment()
                )
                .commit()
        }
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun showError(error: Throwable?) {
        Toast.makeText(this, error?.message, Toast.LENGTH_LONG).show()
    }

    override fun navigateToDetail(id: RestaurantId) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                RestaurantDetailFragment.newInstance(id)
            )
            .addToBackStack("Detail")
            .commit()
    }
}
