package com.qifan.data

import com.qifan.data.entity.toModel
import com.qifan.domain.RestaurantModel
import com.qifan.domain.TheForkRepository
import io.reactivex.Single

class TheForkRepositoryImpl constructor(
    private val theForkService: TheForkService = TheForkService()
) : TheForkRepository {
    override fun getRestaurantDetail(): Single<RestaurantModel> {
        return theForkService.getRestaurantDetail(restaurantId = "40370")
            .map { it.toModel() }
    }
}