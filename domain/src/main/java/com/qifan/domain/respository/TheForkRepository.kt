package com.qifan.domain.respository

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import io.reactivex.Single

typealias RestaurantId = String

interface TheForkRepository {
    fun getRestaurantDetail(id: RestaurantId): Single<Results<RestaurantModel>>
}
