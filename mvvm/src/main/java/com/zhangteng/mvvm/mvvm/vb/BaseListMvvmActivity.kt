package com.zhangteng.mvvm.mvvm.vb

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.base.BaseViewModel
import com.zhangteng.mvvm.mvvm.BaseListMvvmActivity
import com.zhangteng.utils.ViewBindingUtils

/**
 * ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseListMvvmActivity<vb : ViewBinding?, VM : BaseViewModel, D, VH : BaseAdapter.DefaultViewHolder, A : BaseAdapter<D, VH>> :
    BaseListMvvmActivity<VM, D, VH, A>() {

    protected var mBinding: vb? = null

    override fun setContentView(view: View?) {
        mBinding = view?.let { ViewBindingUtils.bind<vb>(this, it) }
        super.setContentView(mBinding?.root ?: view)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) {
        mBinding = view?.let { ViewBindingUtils.bind<vb>(this, it) }
        super.setContentView(mBinding?.root ?: view, params)
    }

    override fun setContentView(layoutResID: Int) {
        mBinding = ViewBindingUtils.inflate<vb>(this)
        super.setContentView(mBinding?.root ?: layoutInflater.inflate(layoutResID, null))
    }
}