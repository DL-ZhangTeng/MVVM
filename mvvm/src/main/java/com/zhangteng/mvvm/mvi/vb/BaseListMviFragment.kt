package com.zhangteng.mvvm.mvi.vb

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.mvvm.BaseListMvvmFragment
import com.zhangteng.utils.ViewBindingUtils

/**
 * ViewModelFragment基类，自动把ViewModel注入Fragment
 */

abstract class BaseListMviFragment<vb : ViewBinding?, VM : BaseViewModel, D, VH : BaseAdapter.DefaultViewHolder, A : BaseAdapter<D, VH>> :
    BaseListMvvmFragment<VM, D, VH, A>() {

    protected var mBinding: vb? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = ViewBindingUtils.bind<vb>(this, view)
        super.onViewCreated(mBinding?.root ?: view, savedInstanceState)
    }
}