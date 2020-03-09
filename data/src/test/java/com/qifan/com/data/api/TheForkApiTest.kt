package com.qifan.com.data.api

import com.qifan.com.data.utils.TheForkApiMockService
import com.qifan.data.api.TheForkApi
import com.qifan.data.entity.Restaurant
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class TheForkApiTest {
    private val mockService by lazy { TheForkApiMockService(TheForkApi::class.java) }


    @Test
    fun when_the_fork_api_send_a_restaurant_response_return_200() {
        mockService.enqueueResponse(200, "restaurant.json")
        val response = mockService.get()
            .getRestaurantDetail(anyString(), anyString(), anyString())
            .blockingGet()
        Assert.assertTrue(response.isSuccessful)
    }


    @Test
    fun when_the_fork_api_send_a_restaurant_response_successfully() {
        mockService.enqueueResponse(200, "restaurant.json")
        val response = mockService.get()
            .getRestaurantDetail(anyString(), anyString(), anyString())
            .blockingGet()
        val restaurant: Restaurant = response.body()!!
        Assert.assertTrue(restaurant.result == 1)
        Assert.assertTrue(restaurant.resultCode == null)
    }
}