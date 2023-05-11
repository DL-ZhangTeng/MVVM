package com.zhangteng.app.mvvm.repository

import android.content.Context
import com.zhangteng.app.di.ApiEntryPoint
import com.zhangteng.app.http.Api
import com.zhangteng.app.http.BaseResult
import com.zhangteng.app.http.entity.HomeListBean
import com.zhangteng.app.http.entity.NavTypeBean
import com.zhangteng.mvvm.base.BaseNetRepository
import dagger.hilt.EntryPoints
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BaseListMvvmDemoRepository @Inject constructor(@ApplicationContext mContext: Context) :
    BaseNetRepository() {

    private val mService: Api by lazy {
        EntryPoints.get(mContext, ApiEntryPoint::class.java).getApi()
    }

    suspend fun getProjectList(page: Int, cid: Int): BaseResult<HomeListBean> {
        return mService.getProjectList(page, cid)
    }

    /**
     * 先请求tab数据
     * */
    suspend fun getData(): BaseResult<List<NavTypeBean>> {
        return mService.naviJson()
    }

    /**
     * 用Flow流的方式
     * 操作符比较繁琐
     * */
    suspend fun getFirstData(): BaseResult<List<NavTypeBean>> {
        return mService.naviJson()
    }
}