package com.qifan.domain.usecase.list

import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.usecase.UseCase
import io.reactivex.Single

interface GetRestaurantListUseCase : UseCase {
    fun getRestaurantList(restaurantIds: List<RestaurantId>): Single<List<RestaurantBannerModel>>
}