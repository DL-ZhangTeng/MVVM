package com.zhangteng.baselibrary.mvvm.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class BaseMvvmDemoFragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}