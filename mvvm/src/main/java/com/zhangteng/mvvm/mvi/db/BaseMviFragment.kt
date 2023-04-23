package com.zhangteng.mvvm.mvi.db

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhangteng.mvvm.BR
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.mvi.BaseMviFragment

/**
 * ViewModelFragment基类，自动把ViewModel注入Fragment和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseMviFragment<DB : ViewDataBinding, VM : BaseViewModel> :
    BaseMviFragment<VM>() {

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mDatabind = DataBindingUtil.bind(view)!!
        mDatabind.setVariable(BR.viewModel, mViewModel)
        mDatabind.lifecycleOwner = this
        super.onViewCreated(mDatabind.root, savedInstanceState)
    }
}