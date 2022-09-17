package com.zhangteng.app.mvvm.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class BaseListMvvmDemoFragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.instance.ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}