package com.qifan.domain.usecase.detail

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetRestaurantDetailUseCaseImpl @Inject constructor(
    private val theForkRepository: TheForkRepository
) : GetRestaurantDetailUseCase {
    override fun getRestaurantDetail(): Single<RestaurantModel> {
        return theForkRepository.getRestaurantDetail()
            .subscribeOn(Schedulers.io())
    }
}