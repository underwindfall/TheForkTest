package com.qifan.theforktest.ui

import android.os.Bundle
import com.qifan.theforktest.R
import com.qifan.theforktest.ui.base.view.activity.InjectionActivity
import com.qifan.theforktest.ui.fragment.list.RestaurantListFragment

class RestaurantActivity : InjectionActivity() {

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

}
