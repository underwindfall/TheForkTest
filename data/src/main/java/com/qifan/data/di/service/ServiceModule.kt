package com.qifan.data.di.service

import com.qifan.data.api.TheForkApi
import com.qifan.data.api.TheForkService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class ServiceModule {
    @Singleton
    @Provides
    fun bindTheForkService(theForkApi: TheForkApi): TheForkService {
        return TheForkService(api = theForkApi)
    }
}
