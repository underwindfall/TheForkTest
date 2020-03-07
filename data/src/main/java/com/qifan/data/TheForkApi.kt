package com.qifan.data

import com.google.gson.GsonBuilder
import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
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

        fun create(): TheForkApi {
            return with(
                GsonBuilder()
                    .serializeNulls()
                    .create()
            ) {
                Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(this))
                    .baseUrl(ENDPOINT)
                    .build()
                    .create()
            }
        }
    }
}