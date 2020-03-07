package com.qifan.domain.usecase.list

import com.qifan.domain.extension.io
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRestaurantListUseCaseImp @Inject constructor(
    private val theForkRepository: TheForkRepository
) : GetRestaurantListUseCase {
    override fun getRestaurantList(restaurantIds: List<RestaurantId>): Single<List<RestaurantBannerModel>> {
        return theForkRepository.getRestaurantList(restaurantIds)
            .io()
    }
}