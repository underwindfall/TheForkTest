package com.qifan.theforktest.di.module

import com.qifan.theforktest.di.module.restaurant.RestaurantFragmentModule
import com.qifan.theforktest.di.scope.PerActivity
import com.qifan.theforktest.ui.RestaurantActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [RestaurantFragmentModule::class])
    internal abstract fun restaurantActivity(): RestaurantActivity
}