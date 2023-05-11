package com.zhangteng.app.di

import com.zhangteng.app.http.Api
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ApiEntryPoint {
    fun getApi(): Api
}