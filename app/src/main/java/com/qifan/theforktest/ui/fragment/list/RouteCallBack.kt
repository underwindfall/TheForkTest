package com.qifan.theforktest.ui.fragment.list

import com.qifan.domain.respository.RestaurantId

interface RouteCallBack {
    fun navigateToDetail(id: RestaurantId)
}