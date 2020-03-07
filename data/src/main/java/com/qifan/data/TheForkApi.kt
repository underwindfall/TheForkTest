package com.qifan.data

import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

typealias ApiKey = String
typealias ApiMethod = String
typealias ApiRequestId = String

interface TheForkApi {
    @GET("api")
    fun getRestaurantDetail(
        @Query("key")
        key: ApiKey = KEY,
        @Query("method")
        method: ApiMethod = METHOD,
        @Query("id_restaurant")
        restaurantId: ApiRequestId
    ): Single<Restaurant>

    companion object {
        const val HOST = "api.lafourchette.com/"
        const val ENDPOINT = "http://$HOST"

        private const val KEY = "IPHONEPRODEDCRFV"
        private const val METHOD = "restaurant_get_info"
    }
}