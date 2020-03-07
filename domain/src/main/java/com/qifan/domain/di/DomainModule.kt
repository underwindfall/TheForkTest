package com.qifan.domain.di

import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCase
import com.qifan.domain.usecase.detail.GetRestaurantDetailUseCaseImpl
import com.qifan.domain.usecase.list.GetRestaurantListUseCase
import com.qifan.domain.usecase.list.GetRestaurantListUseCaseImp
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {
    @Binds
    abstract fun bindGetResDetailUseCase(getRestaurantDetailUseCaseImpl: GetRestaurantDetailUseCaseImpl): GetRestaurantDetailUseCase

    @Binds
    abstract fun bindGetRestaurantListUseCase(getRestaurantListUseCase: GetRestaurantListUseCaseImp): GetRestaurantListUseCase
}