package com.qifan.domain.usecase.detail

import com.qifan.domain.extension.io
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import javax.inject.Inject

class GetRestaurantDetailUseCaseImpl @Inject constructor(
    private val theForkRepository: TheForkRepository
) : GetRestaurantDetailUseCase {
    override fun getRestaurantDetail(id: RestaurantId): Single<RestaurantModel> {
        return theForkRepository.getRestaurantDetail(id)
            .io()
    }
}