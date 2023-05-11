package com.zhangteng.app

import android.os.Bundle
import android.view.View
import com.zhangteng.app.activity.BaseListMviDemoActivity
import com.zhangteng.app.activity.BaseListMvvmDbDemoActivity
import com.zhangteng.app.activity.BaseListMvvmDemoActivity
import com.zhangteng.app.ui.mvvm.MvvmActivity
import com.zhangteng.base.base.BaseActivity
import com.zhangteng.utils.jumpToActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    fun onClickMvvm(v: View) {
        jumpToActivity<MvvmActivity>()
    }

    fun onClickMvvmDb(v: View) {
        jumpToActivity<com.zhangteng.app.ui.mvvmdb.MvvmActivity>()
    }

    fun onClickListMvvm(v: View) {
        jumpToActivity<BaseListMvvmDemoActivity>()
    }

    fun onClickListMvvmDb(v: View) {
        jumpToActivity<BaseListMvvmDbDemoActivity>()
    }

    fun onClickListMvi(v: View) {
        jumpToActivity<BaseListMviDemoActivity>()
    }
}