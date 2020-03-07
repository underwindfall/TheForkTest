package com.qifan.theforktest.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.qifan.theforktest.ui.fragment.detail.RestaurantDetailViewModel
import com.qifan.theforktest.ui.fragment.list.RestaurantListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantListViewModel::class)
    abstract fun bindsListViewModel(restaurantListViewModel: RestaurantListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RestaurantDetailViewModel::class)
    abstract fun bindsDetailViewModel(detailViewModel: RestaurantDetailViewModel): ViewModel

}