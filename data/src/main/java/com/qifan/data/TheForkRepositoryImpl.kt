package com.qifan.data

import com.qifan.data.api.TheForkService
import com.qifan.data.extension.rx.transfomer.restaurantResponseToModelResult
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import javax.inject.Inject


class TheForkRepositoryImpl @Inject constructor(
    private val theForkService: TheForkService
) : TheForkRepository {

    override fun getRestaurantDetail(id: RestaurantId): Single<Results<RestaurantModel>> {
        return theForkService.getRestaurantDetail(restaurantId = id)
            .compose(restaurantResponseToModelResult())
    }
}