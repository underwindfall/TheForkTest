package com.qifan.data.di.service

import com.qifan.data.TheForkApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ApiModule {
    @Singleton
    @Provides
    fun provideTheForkApi(retrofit: Retrofit): TheForkApi {
        return retrofit.create(TheForkApi::class.java)
    }
}