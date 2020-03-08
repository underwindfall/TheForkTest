package com.qifan.data.mapper

import com.qifan.data.entity.Restaurant
import com.qifan.data.entity.RestaurantDetail
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.model.RestaurantModel
import java.util.*

fun Restaurant.toModel(): RestaurantModel {
    return with(data) {
        RestaurantModel(
            id = id.toString(),
            name = name,
            bannerUrl = banner?.url,
            foodType = speciality,
            slide = slide?.map { it.toModel() },
            rateCount = rateCount.toString(),
            rateDistinction = rateDistinction,
            chefName = chefName,
            tripAdvisorAverageRate = tripAdvisorAverageRate,
            tripAdvisorReviewCount = tripAdvisorReviewCount.toString(),
            avgRate = avgRate,
            avgPrice = if (price != null && currencyCode != null) RestaurantModel.AvgPrice(
                price,
                Currency.getInstance(currencyCode)
            ) else null,
            startMenu1 = RestaurantModel.MenuItem(
                menuStart1,
                priceStart1,
                Currency.getInstance(currencyCode)
            ),
            startMenu2 = RestaurantModel.MenuItem(
                menuStart2,
                priceStart2,
                Currency.getInstance(currencyCode)
            ),
            startMenu3 = RestaurantModel.MenuItem(
                menuStart3,
                priceStart3,
                Currency.getInstance(currencyCode)
            ),
            mainMenu1 = RestaurantModel.MenuItem(
                menuMain1,
                priceMain1,
                Currency.getInstance(currencyCode)
            ),
            mainMenu2 = RestaurantModel.MenuItem(
                menuMain2,
                priceMain2,
                Currency.getInstance(currencyCode)
            ),
            mainMenu3 = RestaurantModel.MenuItem(
                menuMain3,
                priceMain3,
                Currency.getInstance(currencyCode)
            ),
            dessertMenu1 = RestaurantModel.MenuItem(
                menuDessert1,
                priceDessert1,
                Currency.getInstance(currencyCode)
            ),
            dessertMenu2 = RestaurantModel.MenuItem(
                menuDessert2,
                priceDessert2,
                Currency.getInstance(currencyCode)
            ),
            dessertMenu3 = RestaurantModel.MenuItem(
                menuDessert3,
                priceDessert3,
                Currency.getInstance(currencyCode)
            )
        )
    }
}


fun RestaurantDetail.RestaurantSlide.toModel(): RestaurantModel.Slide {
    return RestaurantModel.Slide(
        url,
        label
    )
}

fun Restaurant.toBannerModel(): RestaurantBannerModel {
    return with(data) {
        RestaurantBannerModel(
            id = id.toString(),
            name = name,
            bannerUrl = banner?.url,
            speciality = speciality,
            price = if (price != null && currencyCode != null) RestaurantBannerModel.Price(
                price,
                Currency.getInstance(currencyCode)
            ) else null,
            averageRate = avgRate
        )
    }
}