package com.qifan.com.data.utils

import com.qifan.data.entity.ErrorType
import com.qifan.data.entity.Restaurant
import com.qifan.data.entity.RestaurantDetail

fun buildRestaurant(errorType: ErrorType? = null): Restaurant {
    return Restaurant(
        result = 1,
        resultCode = "",
        data = buildRestaurantDetail(),
        errorType = errorType
    )
}

fun buildRestaurantDetail(): RestaurantDetail {
    return RestaurantDetail(
        id = 40370L,
        name = "L'épicerie Saint-Sabin",
        banner = buildBanner(),
        slide = buildBanners(),
        speciality = "speciality",
        price = 0,
        currencyCode = "EUR",
        rateCount = 1L,
        chefName = "chefName",
        rateDistinction = "rateDistinction",
        tripAdvisorAverageRate = 1F,
        tripAdvisorReviewCount = 1,
        avgRate = 1.0,
        menuStart1 = "menu",
        menuStart2 = "menu",
        menuStart3 = "menu",
        menuMain1 = "menu",
        menuMain2 = "menu",
        menuMain3 = "menu",
        menuDessert1 = "menu",
        menuDessert2 = "menu",
        menuDessert3 = "menu",
        priceStart1 = 9F,
        priceStart2 = 9F,
        priceStart3 = 9F,
        priceMain1 = 9F,
        priceMain2 = 9F,
        priceMain3 = 9F,
        priceDessert1 = 9F,
        priceDessert2 = 9F,
        priceDessert3 = 9F
    )
}

fun buildBanners(): List<RestaurantDetail.RestaurantSlide> {
    return listOf(
        buildBanner(),
        buildBanner(),
        buildBanner()
    )
}

fun buildBanner(): RestaurantDetail.RestaurantSlide {
    return RestaurantDetail.RestaurantSlide(
        url = "",
        label = ""
    )
}