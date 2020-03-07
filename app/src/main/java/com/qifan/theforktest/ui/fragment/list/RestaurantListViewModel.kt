package com.qifan.theforktest.ui.fragment.list

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.theforktest.ui.base.viewmodel.BaseViewModel
import io.reactivex.Single
import javax.inject.Inject

class RestaurantListViewModel @Inject constructor(
    private val getRestaurantDetailUseCase: GetRestaurantDetailUseCase
) : BaseViewModel() {
    fun getDetail(): Single<RestaurantModel> {
        return getRestaurantDetailUseCase.getRestaurantDetail()
    }
}