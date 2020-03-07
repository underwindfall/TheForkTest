package com.qifan.domain.respository

import com.qifan.domain.model.RestaurantModel
import io.reactivex.Single

interface TheForkRepository {
    fun getRestaurantDetail(): Single<RestaurantModel>
}
