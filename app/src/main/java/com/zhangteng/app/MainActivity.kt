package com.zhangteng.app

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import com.zhangteng.app.activity.*
import com.zhangteng.app.ui.mvvm.MvvmActivity
import com.zhangteng.app.ui.mvvmdb.MvvmDbActivity
import com.zhangteng.base.base.BaseActivity
import com.zhangteng.utils.StateViewHelper
import com.zhangteng.utils.jumpToActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {

    }

    override fun initData() {

    }

    fun onClickBaseLayout(v: View) {
        jumpToActivity<BaseDemoActivity>()
    }

    fun onClickTabLayout(v: View) {
        jumpToActivity<TabLayoutActivity>()
    }

    fun onClickTree(v: View) {
        jumpToActivity<TreeActivity>()
    }

    fun onClickNineImage(v: View) {
        jumpToActivity<NineImageActivity>()
    }

    fun onClickMvvm(v: View) {
        jumpToActivity<MvvmActivity>()
    }

    fun onClickMvvmDb(v: View) {
        jumpToActivity<MvvmDbActivity>()
    }

    fun onClickListMvvm(v: View) {
        jumpToActivity<BaseListMvvmDemoActivity>()
    }

    fun onClickListMvvmDb(v: View) {
        jumpToActivity<BaseListMvvmDbDemoDbActivity>()
    }

    override fun createStateViewHelper(): StateViewHelper {
        return StateViewHelper().apply {
            againRequestListener = object : StateViewHelper.AgainRequestListener {
                override fun request(view: View) {
                    againRequestByStateViewHelper(view)
                }
            }
            cancelRequestListener = object : StateViewHelper.CancelRequestListener {
                override fun cancel(dialog: DialogInterface) {
                    cancelRequestByStateViewHelper(dialog)
                }
            }
        }
    }

    override fun showProgressDialog(mLoadingText: String?) {
        mStateViewHelper.showProgressDialog(this, R.drawable.loading5, mLoadingText)
    }

    override fun againRequestByStateViewHelper(view: View) {
        super.againRequestByStateViewHelper(view)

    }

    override fun cancelRequestByStateViewHelper(dialog: DialogInterface) {
        super.cancelRequestByStateViewHelper(dialog)

    }
}