package com.zhangteng.app.di

import com.zhangteng.app.http.Api
import com.zhangteng.httputils.http.HttpUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun createApi(): Api {
        return HttpUtils.instance.ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
