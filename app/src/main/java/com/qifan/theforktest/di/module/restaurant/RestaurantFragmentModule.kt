package com.qifan.theforktest.di.module.restaurant

import com.qifan.theforktest.di.scope.PerFragment
import com.qifan.theforktest.ui.fragment.detail.RestaurantDetailFragment
import com.qifan.theforktest.ui.fragment.list.RestaurantListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RestaurantFragmentModule {
    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindsListFragment(): RestaurantListFragment

    @PerFragment
    @ContributesAndroidInjector
    abstract fun bindsDetailFragment(): RestaurantDetailFragment
}