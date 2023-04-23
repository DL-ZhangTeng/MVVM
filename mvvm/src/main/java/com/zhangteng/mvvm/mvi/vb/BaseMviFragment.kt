package com.zhangteng.mvvm.mvi.vb

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.mvvm.BaseMvvmFragment
import com.zhangteng.utils.ViewBindingUtils

/**
 * ViewModelFragment基类，自动把ViewModel注入Fragment
 */

abstract class BaseMviFragment<vb : ViewBinding?, VM : BaseViewModel> : BaseMvvmFragment<VM>() {

    protected var mBinding: vb? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mBinding = ViewBindingUtils.bind<vb>(this, view)
        super.onViewCreated(mBinding?.root ?: view, savedInstanceState)
    }
}