package com.qifan.theforktest.ui.fragment.detail

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.theforktest.ui.base.viewmodel.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class RestaurantDetailViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase
) : BaseViewModel() {
    fun getDetail(id: RestaurantId): Single<RestaurantModel> {
        return getRestaurantDetailUseCase.getRestaurantDetail(id)
    }
}