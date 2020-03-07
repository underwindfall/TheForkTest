package com.qifan.theforktest.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.qifan.theforktest.R
import com.qifan.theforktest.ui.fragment.list.RestaurantListFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class RestaurantActivity : AppCompatActivity(), HasAndroidInjector {
    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container,
                    RestaurantListFragment()
                )
                .commit()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

}
