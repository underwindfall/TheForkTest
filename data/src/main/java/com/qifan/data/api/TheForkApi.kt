package com.qifan.data.api

import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

typealias ApiKey = String
typealias ApiMethod = String
typealias ApiRequestId = String

interface TheForkApi {
    @GET("api")
    fun getRestaurantDetail(
        @Query("key")
        key: ApiKey,
        @Query("method")
        method: ApiMethod,
        @Query("id_restaurant")
        restaurantId: ApiRequestId
    ): Single<Response<Restaurant>>

    companion object {
        const val HOST = "api.lafourchette.com/"
        const val ENDPOINT = "http://$HOST"
    }
}