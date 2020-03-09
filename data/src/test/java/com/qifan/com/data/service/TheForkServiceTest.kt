package com.qifan.com.data.service

import com.qifan.data.api.TheForkApi
import com.qifan.data.api.TheForkGetRestaurantApi
import com.qifan.data.api.TheForkService
import com.qifan.data.api.service.ForkServiceManager
import com.qifan.data.entity.Restaurant
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Response

class TheForkServiceTest {
    @Mock
    private lateinit var mockTheForkApi: TheForkApi

    @Mock
    private lateinit var mockForkServiceManager: ForkServiceManager

    @Mock
    private lateinit var mockRestaurant: Restaurant

    @Mock
    private lateinit var mockRestaurantResponse: Response<Restaurant>

    private lateinit var theForkGetRestaurantApi: TheForkGetRestaurantApi

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        theForkGetRestaurantApi = TheForkService(mockTheForkApi, mockForkServiceManager)
        given(
            mockTheForkApi.getRestaurantDetail(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
            )
        ).willReturn(
            Single.just(mockRestaurantResponse)
        )

        given(
            mockForkServiceManager.manageHttpResponse(
                mockTheForkApi.getRestaurantDetail(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.anyString()
                ),
                mockRestaurantResponse,
                Restaurant::class.java
            )
        )
            .willReturn(Single.just(mockRestaurant))
    }


    @Test
    fun test_when_api_return_good_result_then_return_restaurant() {
        theForkGetRestaurantApi.getRestaurantDetail(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
        Mockito.verify(mockTheForkApi, times(2)).getRestaurantDetail(
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString(),
            ArgumentMatchers.anyString()
        )
    }
}