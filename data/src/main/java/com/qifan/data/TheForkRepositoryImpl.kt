package com.qifan.data

import com.qifan.data.api.TheForkService
import com.qifan.data.entity.Restaurant
import com.qifan.data.entity.toBannerModel
import com.qifan.data.entity.toModel
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject


class TheForkRepositoryImpl @Inject constructor(
    private val theForkService: TheForkService
) : TheForkRepository {

    override fun getRestaurantDetail(id: RestaurantId): Single<RestaurantModel> {
        return theForkService.getRestaurantDetail(restaurantId = id)
            .map { it.toModel() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getRestaurantList(restaurantIds: List<RestaurantId>):
            Single<List<RestaurantBannerModel>> {
        val getRestaurantDetailSingles = restaurantIds
            .map { theForkService.getRestaurantDetail(restaurantId = it) }
        val restaurantMerger = Function<Array<Any>, List<Restaurant>> {
            it.toList() as List<Restaurant>
        }
        return Single.zip(getRestaurantDetailSingles, restaurantMerger)
            .map { restaurants ->
                restaurants.map { it.toBannerModel() }
            }
    }

}