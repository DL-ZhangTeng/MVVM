package com.zhangteng.app.mvvm.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class TabLayoutDbRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}