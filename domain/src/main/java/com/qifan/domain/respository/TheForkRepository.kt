package com.qifan.domain.respository

import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.model.RestaurantModel
import io.reactivex.Single

typealias RestaurantId = String

interface TheForkRepository {
    fun getRestaurantDetail(id: RestaurantId): Single<RestaurantModel>
    fun getRestaurantList(restaurantIds: List<RestaurantId>): Single<List<RestaurantBannerModel>>
}
