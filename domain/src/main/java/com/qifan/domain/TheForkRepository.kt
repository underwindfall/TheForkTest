package com.qifan.domain

import io.reactivex.Single

interface TheForkRepository {
    fun getRestaurantDetail(): Single<RestaurantModel>
}
