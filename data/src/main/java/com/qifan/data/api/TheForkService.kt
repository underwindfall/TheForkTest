package com.qifan.data.api

import com.qifan.data.api.service.ForkServiceManager
import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton


interface TheForkGetRestaurantApi {
    fun getRestaurantDetail(
        key: ApiKey = KEY,
        method: ApiMethod = METHOD,
        restaurantId: ApiRequestId
    ): Single<Restaurant>

    companion object {
        private const val KEY = "IPHONEPRODEDCRFV"
        private const val METHOD = "restaurant_get_info"
    }
}

@Singleton
class TheForkService @Inject constructor(
    private val api: TheForkApi,
    private val serviceManager: ForkServiceManager
) : TheForkGetRestaurantApi {
    override fun getRestaurantDetail(
        key: ApiKey,
        method: ApiMethod,
        restaurantId: ApiRequestId
    ): Single<Restaurant> {
        val call = api.getRestaurantDetail(key, method, restaurantId)
        return call.flatMap { restaurant ->
            serviceManager.manageHttpResponse(call, restaurant, Restaurant::class.java)
        }
    }
}