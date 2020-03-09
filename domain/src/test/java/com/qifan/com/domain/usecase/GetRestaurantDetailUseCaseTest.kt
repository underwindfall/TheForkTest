package com.qifan.com.domain.usecase

import com.qifan.domain.model.RestaurantModel
import com.qifan.domain.model.base.Results
import com.qifan.domain.respository.TheForkRepository
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCaseImpl
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetRestaurantDetailUseCaseTest {
    @Mock
    private lateinit var theForkRepository: TheForkRepository
    @Mock
    private lateinit var result: Results<RestaurantModel>

    private lateinit var getRestaurantDetailUseCaseImpl: GetRestaurantDetailUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getRestaurantDetailUseCaseImpl = GetRestaurantDetailUseCaseImpl(theForkRepository)
        given(
            theForkRepository.getRestaurantDetail(ArgumentMatchers.anyString())
        ).willReturn(
            Single.just(result)
        )
    }

    @Test
    fun when_GetRestaurantDetailUseCase_should_call_repository() {
        getRestaurantDetailUseCaseImpl.getRestaurantDetail(ArgumentMatchers.anyString())
        Mockito.verify(theForkRepository)
    }

}