package com.qifan.data.di.service

import com.qifan.data.TheForkApi
import com.qifan.data.TheForkService
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
