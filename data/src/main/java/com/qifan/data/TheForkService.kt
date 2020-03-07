package com.qifan.data

import com.qifan.data.entity.Restaurant
import io.reactivex.Single

class TheForkService() : TheForkApi {
    private val theForkApi by lazy { TheForkApi.create() }

    override fun getRestaurantDetail(
        key: ApiKey,
        method: ApiMethod,
        restaurantId: ApiRequestId
    ): Single<Restaurant> {
        return theForkApi.getRestaurantDetail(key, method, restaurantId)
    }
}