package com.qifan.data

import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import javax.inject.Inject

class TheForkService @Inject constructor(
    private val api: TheForkApi
) : TheForkApi {
    override fun getRestaurantDetail(
        key: ApiKey,
        method: ApiMethod,
        restaurantId: ApiRequestId
    ): Single<Restaurant> {
        return api.getRestaurantDetail(key, method, restaurantId)
    }
}