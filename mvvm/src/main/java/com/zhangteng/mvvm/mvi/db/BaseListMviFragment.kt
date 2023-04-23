package com.zhangteng.mvvm.mvi.db

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.BR
import com.zhangteng.mvvm.adapter.BindingAdapter
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.mvvm.BaseListMvvmFragment

/**
 * ViewModelFragment基类，自动把ViewModel注入Fragment和Databind注入进来了
 * 需要使用Databind的清继承它
 */
abstract class BaseListMviFragment<DB : ViewDataBinding, VM : BaseViewModel, D, VH : BindingAdapter.BindingViewHolder<D>, A : BaseAdapter<D, VH>> :
    BaseListMvvmFragment<VM, D, VH, A>() {

    //该类绑定的ViewDataBinding
    lateinit var mDatabind: DB

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mDatabind = DataBindingUtil.bind(view)!!
        mDatabind.setVariable(BR.viewModel, mViewModel)
        mDatabind.lifecycleOwner = this
        super.onViewCreated(mDatabind.root, savedInstanceState)
    }
}