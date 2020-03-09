package com.qifan.data.di.service

import com.qifan.data.api.TheForkApi
import com.qifan.data.api.TheForkService
import com.qifan.data.api.service.ForkServiceManager
import com.qifan.data.api.service.ForkServiceManagerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class ServiceModule {
    @Singleton
    @Provides
    fun provideTheForkService(
        theForkApi: TheForkApi,
        serviceManager: ForkServiceManager
    ): TheForkService {
        return TheForkService(api = theForkApi, serviceManager = serviceManager)
    }

    @Singleton
    @Provides
    fun provideForkServiceManager(): ForkServiceManager {
        return ForkServiceManagerImpl()
    }
}
