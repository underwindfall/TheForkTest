package com.qifan.data.di.service

import com.qifan.data.TheForkApi
import com.qifan.data.TheForkService
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
abstract class ServiceModule {
    @Singleton
    @Binds
    abstract fun bindTheForkService(theForkService: TheForkService): TheForkApi
}
