package com.qifan.data.di.repository

import com.qifan.data.TheForkRepositoryImpl
import com.qifan.domain.respository.TheForkRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindTheForkRepository(theForkRepositoryImpl: TheForkRepositoryImpl): TheForkRepository
}