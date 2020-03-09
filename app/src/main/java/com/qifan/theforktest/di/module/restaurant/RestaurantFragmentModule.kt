package com.qifan.theforktest.di.module.restaurant

import com.qifan.theforktest.di.scope.PerFragment
import com.qifan.theforktest.ui.restaurant.fragment.detail.RestaurantDetailFragment
import com.qifan.theforktest.ui.restaurant.fragment.search.RestaurantSearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RestaurantFragmentModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindsListFragment(): RestaurantSearchFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindsDetailFragment(): RestaurantDetailFragment
}