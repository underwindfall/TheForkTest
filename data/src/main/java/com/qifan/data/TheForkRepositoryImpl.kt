package com.qifan.data

import com.qifan.data.entity.toModel
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import javax.inject.Inject

class TheForkRepositoryImpl @Inject constructor(
    private val theForkService: TheForkService
) : TheForkRepository {
    override fun getRestaurantDetail(): Single<RestaurantModel> {
        return theForkService.getRestaurantDetail(restaurantId = "40370")
            .map { it.toModel() }
    }
}