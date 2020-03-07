package com.qifan.theforktest.ui.fragment.list

import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.usecase.list.GetRestaurantListUseCase
import com.qifan.theforktest.ui.base.viewmodel.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject


class RestaurantListViewModel @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase
) : BaseViewModel() {
    fun getListRestaurant(ids: List<RestaurantId>): Single<List<RestaurantBannerModel>> {
        return getRestaurantListUseCase.getRestaurantList(ids)
    }
}