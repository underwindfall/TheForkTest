package com.qifan.data

import com.qifan.data.api.TheForkService
import com.qifan.data.entity.Restaurant
import com.qifan.data.mapper.toBannerModel
import com.qifan.data.mapper.toModel
import com.qifan.domain.model.RestaurantBannerModel
import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.respository.RestaurantId
import com.qifan.domain.respository.TheForkRepository
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject


class TheForkRepositoryImpl @Inject constructor(
    private val theForkService: TheForkService
) : TheForkRepository {

    override fun getRestaurantDetail(id: RestaurantId): Single<RestaurantModel> {
        return theForkService.getRestaurantDetail(restaurantId = id)
            .map { it.toModel() }
    }

    @Suppress("UNCHECKED_CAST")
    override fun getRestaurantList(restaurantIds: List<RestaurantId>):
            Single<List<RestaurantBannerModel>> {
//        val listRestaurantBannerModels = mutableListOf(
//            RestaurantBannerModel(
//                id = "40370",
//                name = "L'épicerie Saint-Sabin",
//                bannerUrl = "https://u.tfstatic.com/restaurant_photos/370/40370/169/612/l-epicerie-saint-sabin-cote-cave-c351b.jpg",
//                speciality = "Français",
//                price = RestaurantBannerModel.Price(
//                    amount = 35,
//                    currency = Currency.getInstance("EUR")
//                ),
//                averageRate = null
//            ),
//            RestaurantBannerModel(
//                id = "16409",
//                name = "Angeethi",
//                bannerUrl = "https://u.tfstatic.com/restaurant_photos/409/16409/169/612/Devanture.jpg",
//                speciality = "Indien",
//                price = RestaurantBannerModel.Price(
//                    amount = 22,
//                    currency = Currency.getInstance("EUR")
//                ),
//                averageRate = null
//            ),
//            RestaurantBannerModel(
//                id = "14163",
//                name = "Momiji",
//                bannerUrl = "https://u.tfstatic.com/restaurant_photos/163/14163/169/612/momiji-restaurant-japonais-design-82eb3.jpg",
//                speciality = "Japonais",
//                price = RestaurantBannerModel.Price(
//                    amount = 15,
//                    currency = Currency.getInstance("EUR")
//                ),
//                averageRate = null
//            ),
//            RestaurantBannerModel(
//                id = "40171",
//                name = "Novo-Hotel Hesperia Finisterre",
//                bannerUrl = "https://u.tfstatic.com/restaurant_photos/171/40171/169/612/novo-hotel-hesperia-finisterre-hesperia-f2b7e.jpg",
//                speciality = "Gastronomique",
//                price = RestaurantBannerModel.Price(
//                    amount = 40,
//                    currency = Currency.getInstance("EUR")
//                ),
//                averageRate = null
//            )
//        )
//
//        return Single.just(listRestaurantBannerModels)
        val getRestaurantDetailSingles = restaurantIds
            .map { theForkService.getRestaurantDetail(restaurantId = it) }
        val restaurantMerger = Function<Array<Any>, List<Restaurant>> {
            it.toList() as List<Restaurant>
        }
        return Single.zip(getRestaurantDetailSingles, restaurantMerger)
            .map { restaurants ->
                restaurants.map { it.toBannerModel() }
            }
    }

}